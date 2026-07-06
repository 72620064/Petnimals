package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.service.CategoriaService;
import java.util.List;
import pe.edu.petnimals.exportar.ExportarCSV;
import pe.edu.petnimals.exportar.ExportarExcel;
import pe.edu.petnimals.exportar.ExportarPDF;
import pe.edu.petnimals.exportar.ExportarXML;

@RestController
@RequestMapping("/api/categorias") // <--- Al empezar con /api/, ya está protegido por Token
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.obtenerTodas();
    }
    
    @PostMapping
    public Categoria guardarCategoria(@RequestBody Categoria nuevaCategoria) {
        // Al haber modificado tu Service, esto ya no da error y devuelve la Categoria con su ID
        return categoriaService.guardarCategoria(nuevaCategoria); 
    }
    
     @GetMapping("/exportar")
    public String exportarCategorias() {
        List<Categoria> lista = categoriaService.obtenerTodas();

        ExportarPDF pdf = new ExportarPDF();
        pdf.categorias("reporte_categorias.pdf", lista); // Usa el método específico de categorías

        ExportarExcel excel = new ExportarExcel();
        excel.categorias("reporte_categorias.xlsx", lista);
        
        ExportarCSV csv = new ExportarCSV();
        csv.categorias("reporte_categorias.csv", lista);
       
        ExportarXML xml = new ExportarXML();
        xml.categorias("reporte_categorias.xml", lista);
        return "¡Reportes de categorías generados!";
    }
}