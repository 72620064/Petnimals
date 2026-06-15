package pe.edu.petnimals.service;

import pe.edu.petnimals.model.Usuario;
import pe.edu.petnimals.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService { // <- AGREGADO AQUÍ

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- MÉTODO OBLIGATORIO DE SPRING SECURITY ---
    // Este método se ejecuta automáticamente tras bambalinas cuando el usuario presiona "Ingresar"
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo));

        // Convertimos nuestro usuario de MySQL al formato que entiende Spring Security
        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasenia()) // Ya está encriptada en SHA-256
                .roles(usuario.getRol()) // Le pasa el rol (CLIENTE, ADMINISTRADOR, etc.)
                .build();
    }

    // --- TUS MÉTODOS ANTERIORES SE QUEDAN EXACTAMENTE IGUAL ---
    public Usuario registrarCliente(Usuario usuario) throws Exception {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new Exception("El correo electrónico ya se encuentra registrado.");
        }
        String regexPassword = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!usuario.getContrasenia().matches(regexPassword)) {
            throw new Exception("La contraseña debe tener mínimo 8 caracteres, una mayúscula y un número.");
        }
        // Encriptamos antes de guardar en la BD
        String claveEncriptada = pe.edu.petnimals.util.SecurityUtils.encriptarContrasenia(usuario.getContrasenia());
        usuario.setContrasenia(claveEncriptada);
        usuario.setRol("CLIENTE");
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarRol(Long usuarioId, String nuevoRol) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado."));
        if (!nuevoRol.equals("CLIENTE") && !nuevoRol.equals("VETERINARIO") && 
            !nuevoRol.equals("ALBERGUE") && !nuevoRol.equals("ADMINISTRADOR")) {
            throw new Exception("El rol especificado no es válido.");
        }
        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}