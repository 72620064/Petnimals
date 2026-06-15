package pe.edu.petnimals.service;

import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método para guardar una categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Método para listar todas las categorías
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }
}