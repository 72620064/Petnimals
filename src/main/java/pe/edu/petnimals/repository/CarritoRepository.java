package pe.edu.petnimals.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.petnimals.model.ItemCarrito;
import pe.edu.petnimals.model.Usuario;
import java.util.List;
import java.util.Optional;
import pe.edu.petnimals.model.Producto;

@Repository
public interface CarritoRepository extends JpaRepository<ItemCarrito, Long> {
    List<ItemCarrito> findByUsuario(Usuario usuario);

    public Optional<ItemCarrito> findByUsuarioAndProducto(Usuario usuario, Producto producto);
}