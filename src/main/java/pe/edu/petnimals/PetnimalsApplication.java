    package pe.edu.petnimals;

import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.model.Usuario;
import pe.edu.petnimals.model.Rol;
import pe.edu.petnimals.repository.UsuarioRepository;
import pe.edu.petnimals.service.CategoriaService;
import pe.edu.petnimals.util.SecurityUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetnimalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetnimalsApplication.class, args);
    }

    // Este bloque se ejecuta automáticamente justo después de encender el servidor
    @Bean
    public CommandLineRunner ejecutarPrueba(CategoriaService categoriaService, UsuarioRepository usuarioRepository) {
        return args -> {
            
            // 1. POBLAR CATEGORÍAS (Para tu Navbar Dinámico)
            if (categoriaService.obtenerTodas().isEmpty()) {
                System.out.println(">>> Insertando categorías iniciales en la base de datos...");
                
                String[] nombresCategorias = {"Alimentos", "Juguetes", "Accesorios", "Medicamentos", "Higiene", "Camas"};
                
                for (String nombreCat : nombresCategorias) {
                    Categoria cat = new Categoria();
                    cat.setNombre(nombreCat);
                    categoriaService.guardarCategoria(cat);
                }
                
                System.out.println(">>> ¡Categorías guardadas con éxito!");
            }

            // 2. POBLAR ADMINISTRADORES (Cuentas protegidas para el equipo de desarrollo)
            if (usuarioRepository.count() == 0) {
                System.out.println(">>> Creando cuentas de Administradores con contraseñas encriptadas...");
                
                // Encriptamos la contraseña "Certus123*" para cada uno en formato BCrypt
                String clavePiero = SecurityUtils.encriptarContrasenia("Certus123*");
                String claveFernando = SecurityUtils.encriptarContrasenia("Certus123*");
                String claveJunior = SecurityUtils.encriptarContrasenia("Certus123*");

                // Instanciamos el objeto Rol apuntando al ID 1 (ADMINISTRADOR) creado en Workbench
                Rol rolAdmin = new Rol();
                rolAdmin.setIdRol(1L); // El ID 1 corresponde a ADMINISTRADOR en tu tabla 'roles'

                // Registro del Administrador 1: Piero
                Usuario user1 = new Usuario();
                user1.setNombres("Piero");
                user1.setApellidos("Sandoval");
                user1.setCorreo("72620064@certus.edu.pe");
                user1.setPassword(clavePiero);
                user1.setTelefono("993867737");
                user1.setRol(rolAdmin); 
                usuarioRepository.save(user1);

                // Registro del Administrador 2: Fernando
                Usuario user2 = new Usuario();
                user2.setNombres("Fernando");
                user2.setApellidos("Velasquez");
                user2.setCorreo("difertota@gmail.com");
                user2.setPassword(claveFernando);
                user2.setTelefono("975149824");
                user2.setRol(rolAdmin);
                usuarioRepository.save(user2);

                // Registro del Administrador 3: Junior
                Usuario user3 = new Usuario();
                user3.setNombres("Junior");
                user3.setApellidos("Chirinos");
                user3.setCorreo("74700717@certus.edu.pe");
                user3.setPassword(claveJunior);
                user3.setTelefono("912553872");
                user3.setRol(rolAdmin);
                usuarioRepository.save(user3);
                
                System.out.println(">>> ¡Cuentas de administrador protegidas y guardadas exitosamente!");
            }
        };
    }
}