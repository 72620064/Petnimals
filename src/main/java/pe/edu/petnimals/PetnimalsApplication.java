package pe.edu.petnimals;

import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.model.Usuario;
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
                categoriaService.guardarCategoria(new Categoria("Alimentos"));
                categoriaService.guardarCategoria(new Categoria("Juguetes"));
                categoriaService.guardarCategoria(new Categoria("Accesorios"));
                categoriaService.guardarCategoria(new Categoria("Medicamentos"));
                categoriaService.guardarCategoria(new Categoria("Higiene"));
                categoriaService.guardarCategoria(new Categoria("Camas"));
                System.out.println(">>> ¡Categorías guardadas con éxito!");
            }

            // 2. POBLAR ADMINISTRADORES (Cuentas protegidas para el equipo de desarrollo)
            if (usuarioRepository.count() == 0) {
                System.out.println(">>> Creando cuentas de Administradores con contraseñas encriptadas...");
                
                // Encriptamos una contraseña para cada uno antes de guardarla en MySQL
                // Tip: Cambien el texto entre comillas por la contraseña que deseen usar.
                String clavePiero = SecurityUtils.encriptarContrasenia("Certus123*");
                String claveAndre = SecurityUtils.encriptarContrasenia("Certus123*");
                String claveJunior = SecurityUtils.encriptarContrasenia("Certus123*");

                // Registramos los 3 administradores oficiales con sus correos reales
                usuarioRepository.save(new Usuario("Piero Sandoval", "72620064@certus.edu.pe", clavePiero, "ADMINISTRADOR"));
                usuarioRepository.save(new Usuario("Andre Velasquez", "75349968@certus.edu.pe", claveAndre, "ADMINISTRADOR"));
                usuarioRepository.save(new Usuario("Junior Chirinos", "74700717@certus.edu.pe", claveJunior, "ADMINISTRADOR"));
                
                System.out.println(">>> ¡Cuentas de administrador protegidas y guardadas exitosamente!");
            }
        };
    }
}