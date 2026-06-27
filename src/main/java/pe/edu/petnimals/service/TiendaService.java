package pe.edu.petnimals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.petnimals.model.Producto;
import pe.edu.petnimals.repository.TiendaRepository;
import java.util.List;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    public List<Producto> listarTodos() {
        return tiendaRepository.findAll();
    }

    public Producto guardar(Producto producto) {
        return tiendaRepository.save(producto);
    }
}