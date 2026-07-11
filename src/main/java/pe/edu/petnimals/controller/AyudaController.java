package pe.edu.petnimals.controller; // Esta línea es fundamental

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AyudaController {

    @GetMapping("/ayuda")
    public String mostrarAyuda() {
        return "ayuda/ayuda"; 
    }
}