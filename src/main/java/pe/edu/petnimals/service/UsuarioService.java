package pe.edu.petnimals.service;

import pe.edu.petnimals.model.Usuario;
import pe.edu.petnimals.model.Rol; // Asegúrate de importar Rol
import pe.edu.petnimals.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- MÉTODO OBLIGATORIO DE SPRING SECURITY ---
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo));

        // Si no tiene rol, por defecto le ponemos "CLIENTE"
        String nombreRol = (usuario.getRol() != null) ? usuario.getRol().getNombre().name() : Rol.TipoRol.CLIENTE.name();

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPassword()) 
                .roles(nombreRol) // Spring Security ya recibe el texto limpio ("CLIENTE", "ADMINISTRADOR", etc.)
                .build();
    }

    // --- MÉTODO DE COMPATIBILIDAD PARA EL REGISTRO TRADICIONAL ---
    public Usuario registrarCliente(Usuario usuario) throws Exception {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new Exception("El correo electrónico ya se encuentra registrado.");
        }
        String regexPassword = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!usuario.getPassword().matches(regexPassword)) {
            throw new Exception("La contraseña debe tener mínimo 8 caracteres, una mayúscula y un número.");
        }
        String claveEncriptada = pe.edu.petnimals.util.SecurityUtils.encriptarContrasenia(usuario.getPassword());
        usuario.setPassword(claveEncriptada);
        
        // El rol se inicializa en null o puedes buscar el objeto Rol "CLIENTE" desde un RolRepository si lo tienes.
        usuario.setRol(null); 
        
        return usuarioRepository.save(usuario);
    }

    // --- MÉTODO PARA ACTUALIZAR EL ROL ---
    public Usuario actualizarRol(Long usuarioId, Rol nuevoRol) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado."));
        
        // Al usar el enum interno, ya no necesitas los pesados .equals("CLIENTE") ni validaciones manuales.
        // El compilador de Java garantiza que 'nuevoRol' solo contenga un Rol válido de la base de datos.
        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}