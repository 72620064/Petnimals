package pe.edu.petnimals.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.petnimals.model.Producto;
import pe.edu.petnimals.service.TiendaService;
import pe.edu.petnimals.service.CategoriaService; // <--- Importamos tu servicio de categorías

@Controller
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @Autowired
    private CategoriaService categoriaService; // <--- Inyectamos tu servicio

    // 1. Catálogo principal: http://localhost:8080/tienda
    @GetMapping
    public String listarTienda(Model model) {
        model.addAttribute("productos", tiendaService.listarTodos());
        
        // CORRECCIÓN CLAVE: Usamos el nombre exacto que tu Navbar espera encontrar
        model.addAttribute("listaCategorias", categoriaService.obtenerTodas());
        
        return "tienda/productos"; 
    }

    // 2. Formulario: http://localhost:8080/tienda/registrar-productos
    @GetMapping("/registrar-productos")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("tienda", new Producto());
        
        model.addAttribute("listaCategorias", categoriaService.obtenerTodas()); 
        
        return "admin/registrar-productos"; 
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {

        tiendaService.guardar(producto); // Guarda en MySQL mediante el Repository
        return "redirect:/tienda";
    }
}