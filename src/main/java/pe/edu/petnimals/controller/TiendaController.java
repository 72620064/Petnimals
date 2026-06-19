package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.petnimals.model.Producto;
import pe.edu.petnimals.service.TiendaService;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    // 1. Catálogo principal: http://localhost:8080/tienda
    @GetMapping
    public String listarTienda(Model model) {
        model.addAttribute("productos", tiendaService.listarTodos());
        return "tienda/productos"; 
    }

    // 2. Formulario: http://localhost:8080/tienda/registrar-productos
    @GetMapping("/registrar-productos")
    public String mostrarFormularioRegistro(Model model) {
        // Enviamos la esencia 'Producto' vinculada a la variable 'tienda' para complacer a Thymeleaf
        model.addAttribute("tienda", new Producto());
        return "admin/registrar-productos"; 
    }

    // 3. Procesar y guardar en BD
    @PostMapping("/guardar")
    public String guardarEnTienda(@ModelAttribute("tienda") Producto producto) {
        tiendaService.guardar(producto);
        return "redirect:/tienda"; 
    }
}