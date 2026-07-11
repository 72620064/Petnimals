package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import pe.edu.petnimals.model.*;
import pe.edu.petnimals.repository.*;
import pe.edu.petnimals.service.CategoriaService;
import pe.edu.petnimals.service.TiendaService;

@Controller
@RequestMapping("/tienda")
public class TiendaController { // Solo esta clase debe estar aquí

    @Autowired private TiendaRepository productoRepository;
    @Autowired private CarritoRepository carritoRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    
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
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("producto", new Producto());
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

    @PostMapping("/carrito/agregar/{id}")
    public String agregarAlCarrito(@PathVariable("id") Long id, Principal principal) {
        if (principal == null) return "redirect:/login";
        
        Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        ItemCarrito item = new ItemCarrito();
        item.setUsuario(usuario);
        item.setProducto(producto);
        item.setCantidad(1);
        
        carritoRepository.save(item);
        return "redirect:/carrito"; 
    }
}