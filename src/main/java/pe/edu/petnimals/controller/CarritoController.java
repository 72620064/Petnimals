package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import pe.edu.petnimals.model.*;
import pe.edu.petnimals.repository.*;

@Controller
public class CarritoController { // Solo debe estar esta clase aquí

    @Autowired private CarritoRepository carritoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @GetMapping("/carrito")
    public String verCarrito(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<ItemCarrito> items = carritoRepository.findByUsuario(usuario);
        
        List<Producto> productosCarrito = items.stream()
                .map(ItemCarrito::getProducto)
                .collect(Collectors.toList());

        double total = productosCarrito.stream()
                .mapToDouble(p -> p.getPrecio() != null ? p.getPrecio().doubleValue() : 0.0)
                .sum();

        model.addAttribute("carrito", productosCarrito);
        model.addAttribute("total", total);
        
        return "tienda/carrito";
    }
    
    @GetMapping("/finalizar_compra")
    public String finalizarCompra() {

        // aquí va la lógica:
        // guardar compra
        // vaciar carrito
        // mostrar confirmación

        return "redirect:/tienda";
    }
}