package pe.edu.petnimals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.petnimals.model.Producto;

@Repository
public interface TiendaRepository extends JpaRepository<Producto, Long> {
    // Sigue manejando la entidad Producto, pero el repositorio ya se llama Tienda
}