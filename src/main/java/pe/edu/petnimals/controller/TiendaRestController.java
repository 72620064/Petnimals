package pe.edu.petnimals.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.petnimals.model.Producto;
import pe.edu.petnimals.service.TiendaService;

@RestController
@RequestMapping("/api/productos") // convención estándar para APIs
public class TiendaRestController {

    @Autowired
    private TiendaService tiendaService;

    // 1. OBTENER TODOS LOS PRODUCTOS (Para probar con GET en Postman)
    // URL: http://localhost:8080/api/productos
    @GetMapping
    public List<Producto> listarProductos() {
        return tiendaService.listarTodos(); 
        // Spring Boot convierte automáticamente esta lista de objetos Java a formato JSON
    }

    // 2. CREAR UN NUEVO PRODUCTO DESDE POSTMAN (Para probar con POST)
    // URL: http://localhost:8080/api/productos
    // RECUERDA: La URL base arriba ya es @RequestMapping("/api/productos")

    @PostMapping // Indica que este método reaccionará a peticiones POST
    public Producto guardarProducto(@RequestBody Producto nuevoProducto) {
        // @RequestBody es la clave: agarra el JSON de Postman y lo transforma en un objeto Java Producto
        return tiendaService.guardar(nuevoProducto); 
        // Recuerda usar Ctrl + Espacio si tu método del servicio se llama "registrar" o "save"
    }
}