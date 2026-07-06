package pe.edu.petnimals.controller;

import pe.edu.petnimals.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/veterinaria")
    public String mostrarVeterinaria(Model model) {
        // Es vital pasar la listaCategorias para que el Navbar fragment no falle
        model.addAttribute("listaCategorias", categoriaService.obtenerTodas());
        return "veterinaria/reservar_cita"; 
    }
    
    
}