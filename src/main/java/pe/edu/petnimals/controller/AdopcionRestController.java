package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.petnimals.model.Animal;
import pe.edu.petnimals.service.AnimalService;
import java.util.List;
import pe.edu.petnimals.exportar.ExportarCSV;
import pe.edu.petnimals.exportar.ExportarExcel;
import pe.edu.petnimals.exportar.ExportarPDF;
import pe.edu.petnimals.exportar.ExportarXML;

@RestController
@RequestMapping("/api/animales") // <--- Al empezar con /api/, ya está protegido por Token
public class AdopcionRestController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> listarAnimales() {
        return animalService.findAll();
    }
    
    @PostMapping
    public Animal guardarAnimal(@RequestBody Animal nuevoAnimal) {
        return animalService.guardarAnimal(nuevoAnimal); 
    }
    
     @GetMapping("/exportar")
    public String exportarAnimales() {
        List<Animal> lista = animalService.findAll();

        ExportarPDF pdf = new ExportarPDF();
        pdf.animales("reporte_animales.pdf", lista); // Usa el método específico de categorías

        ExportarExcel excel = new ExportarExcel();
        excel.animales("reporte_animales.xlsx", lista);
        
        ExportarCSV csv = new ExportarCSV();
        csv.animales("reporte_animales.csv", lista);
       
        ExportarXML xml = new ExportarXML();
        xml.animales("reporte_animales.xml", lista);
        return "¡Reportes de animales generados con éxito!";
    }
}