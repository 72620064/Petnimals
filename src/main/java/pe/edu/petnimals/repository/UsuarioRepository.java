package pe.edu.petnimals.repository;

import pe.edu.petnimals.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Este método buscará un usuario por su correo en MySQL. 
    // Nos servirá para cumplir la regla de negocio RN-01.
    Optional<Usuario> findByCorreo(String correo);
}