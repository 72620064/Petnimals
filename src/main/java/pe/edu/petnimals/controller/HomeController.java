package pe.edu.petnimals.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.service.CategoriaService;

@Controller
public class HomeController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @ModelAttribute("listaCategorias")
    public List<Categoria> cargarCategoriasAlNavbar() {
        return categoriaService.obtenerTodas(); // Usa el método que tengas para traer la lista
    }
    
    @GetMapping({"/", "/index"})
    public String inicio(Model model) {
        model.addAttribute("listaCategorias", categoriaService.obtenerTodas());
        return "index";
    }
}