package pe.edu.petnimals.controller;

import pe.edu.petnimals.model.Usuario;
import pe.edu.petnimals.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. MUESTRA EL FORMULARIO DE REGISTRO
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/registro"; // <- Cambiado: Apunta a templates/usuario/registro.html
    }

    // 2. PROCESA EL REGISTRO DEL CLIENTE
    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.registrarCliente(usuario);
            return "redirect:/login?exito";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "usuario/registro"; // <- Cambiado: En caso de error regresa a la carpeta usuario
        }
    }

    // 3. MUESTRA EL FORMULARIO DE LOGIN
    @GetMapping("/login")
    public String mostrarLogin() {
        return "usuario/login"; // <- Cambiado: Apunta a templates/usuario/login.html
    }
}