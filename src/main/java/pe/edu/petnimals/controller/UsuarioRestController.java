package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.petnimals.model.Usuario;
import pe.edu.petnimals.service.UsuarioService; // Ajusta según tu nombre exacto de servicio
import java.util.List;
import pe.edu.petnimals.exportar.ExportarCSV;
import pe.edu.petnimals.exportar.ExportarExcel;
import pe.edu.petnimals.exportar.ExportarPDF;
import pe.edu.petnimals.exportar.ExportarXML;

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
    
    @GetMapping("/exportar")
    public String exportarUsuarios() {
        // Obtenemos la lista real de la base de datos
        List<Usuario> lista = usuarioService.obtenerTodosLosUsuarios();

        // Instanciamos los utilitarios
        ExportarPDF pdf = new ExportarPDF();
        ExportarExcel excel = new ExportarExcel();
        ExportarCSV csv = new ExportarCSV();
        ExportarXML xml = new ExportarXML();

        // CORREGIDO: Llamamos a .usuarios() en lugar de .categorias()
        pdf.usuarios("reporte_usuarios.pdf", lista); 
        excel.usuarios("reporte_usuarios.xlsx", lista);
        csv.usuarios("reporte_usuarios.csv", lista);
        xml.usuarios("reporte_usuarios.xml", lista);
        
        return "¡Reportes de usuarios generados con éxito!";
    }
}