package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        
        // Aquí también usamos tu servicio para llenar el combo del formulario
        // Ojo: Si en tu HTML de registrar-productos pusiste th:each="cat : ${categorias}", 
        // cámbialo ahí también por ${listaCategorias} para que coincida.
        model.addAttribute("listaCategorias", categoriaService.obtenerTodas()); 
        
        return "admin/registrar-productos"; 
    }

    @PostMapping("/guardar")
    public String guardarEnTienda(@ModelAttribute("tienda") Producto producto) {
        tiendaService.guardar(producto);
        return "redirect:/tienda"; 
    }
}