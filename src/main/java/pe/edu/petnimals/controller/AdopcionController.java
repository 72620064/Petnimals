package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.petnimals.model.Animal;
import pe.edu.petnimals.service.AnimalService;

@Controller
@RequestMapping("/adopciones")
public class AdopcionController {

    @Autowired
    private AnimalService animalService;

    // LISTAR ANIMALES
    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("animales", animalService.findAll());
        return "adopciones/lista";
    }
    
    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("animales", animalService.findAll());
        return "adopciones/lista";
    }

    // MOSTRAR FORMULARIO
    @GetMapping("/publicar")
    public String publicar(Model model) {
        model.addAttribute("animal", new Animal());
        return "adopciones/publicar"; 
        // 👆 carpeta + archivo
    }

    // GUARDAR ANIMAL
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Animal animal,
                          RedirectAttributes redirectAttributes) {

        animalService.save(animal);

        redirectAttributes.addFlashAttribute(
            "msg",
            "Animal publicado correctamente 🐾"
        );

        return "redirect:/adopciones/lista";
    }

    // ADOPTAR (ELIMINAR)
    @PostMapping("/adoptar")
    public String adoptar(@RequestParam Long idAnimal) {

        animalService.deleteById(idAnimal);

        return "redirect:/adopciones/lista";
    }
}