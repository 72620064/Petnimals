package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.petnimals.model.Usuario;
import pe.edu.petnimals.service.UsuarioService; // Ajusta según tu nombre exacto de servicio
import java.util.List;

@RestController
@RequestMapping("/api/usuarios") // Protegido automáticamente bajo /api/**
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        // Recuerda usar Ctrl + Espacio si tu método se llama diferente (ej: findAll)
        return usuarioService.obtenerTodosLosUsuarios(); 
    }
}