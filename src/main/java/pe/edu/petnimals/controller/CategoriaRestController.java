package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.service.CategoriaService;
import java.util.List;

@RestController
@RequestMapping("/api/categorias") // <--- Al empezar con /api/, ya está protegido por Token
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.obtenerTodas();
    }
    
    @PostMapping
    public Categoria guardarCategoria(@RequestBody Categoria nuevaCategoria) {
        // Al haber modificado tu Service, esto ya no da error y devuelve la Categoria con su ID
        return categoriaService.guardarCategoria(nuevaCategoria); 
    }
}