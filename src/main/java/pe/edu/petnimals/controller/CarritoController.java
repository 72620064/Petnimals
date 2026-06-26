package pe.edu.petnimals.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.edu.petnimals.service.CategoriaService;

@Controller
public class CarritoController {

    @Autowired
    private CategoriaService categoriaService;
    
    // FUSIONADO: Un solo método para /carrito que inyecta las categorías y va a la vista correcta
    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        // Pasamos la listaCategorias para que el fragmento del Navbar (Menú) cargue sin errores
        model.addAttribute("listaCategorias", categoriaService.obtenerTodas());
        return "tienda/carrito"; 
    }
    
    @GetMapping("/finalizar_compra")
    public String verFinalizarCompra(Model model) {
        // También se lo agregamos aquí por si el Navbar se visualiza en esta pantalla
        model.addAttribute("listaCategorias", categoriaService.obtenerTodas());
        return "tienda/finalizar_compra"; 
    }
}