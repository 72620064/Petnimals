package pe.edu.petnimals.exportar;

import pe.edu.petnimals.model.Producto; // Tu modelo real
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import java.util.List;
import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.model.Usuario;

public class ExportarPDF {
    public void productos(String ruta, List<Producto> productos) {
        try {
            PdfWriter writer = new PdfWriter(ruta);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);
            
            doc.add(new Paragraph("REPORTE DE PRODUCTOS"));
            
            Table table = new Table(4);
            table.addCell("ID");
            table.addCell("NOMBRE");
            table.addCell("DECRIPCION");
            table.addCell("PRECIO");
            table.addCell("STOCK");
            
            for (Producto p : productos) {
                table.addCell(String.valueOf(p.getIdProducto()));
                table.addCell(p.getNombre());
                table.addCell(String.valueOf(p.getDescripcion()));
                table.addCell(String.valueOf(p.getPrecio()));
                table.addCell(String.valueOf(p.getStock()));
            }
            
            doc.add(table);
            doc.close();
            System.out.println("PDF generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en PDF: " + e.getMessage());
        }
    }
    
    public void categorias(String ruta, List<Categoria> categorias) {
        try {
            PdfWriter writer = new PdfWriter(ruta);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);
            
            doc.add(new Paragraph("REPORTE DE CATEGORÍAS"));
            Table table = new Table(2); // 3 columnas
            table.addCell("ID"); table.addCell("NOMBRE");
            
            for (Categoria c : categorias) {
                table.addCell(String.valueOf(c.getIdCategoria())); // Ajusta a tu getter real
                table.addCell(c.getNombre());
            }
            doc.add(table);
            doc.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
    
    public void usuarios(String ruta, List<Usuario> usuarios) {
        try {
            PdfWriter writer = new PdfWriter(ruta);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);
            
            doc.add(new Paragraph("REPORTE DE USUARIOS"));
            Table table = new Table(5); 
            table.addCell("ID"); 
            table.addCell("NOMBRES");
            table.addCell("APELLIDOS"); 
            table.addCell("EMAIL");
            table.addCell("TELEFONO"); 
            
            for (Usuario u : usuarios) {
                table.addCell(String.valueOf(u.getIdUsuario())); // Ajusta a tu getter real
                table.addCell(u.getNombres());
                table.addCell(u.getApellidos());
                table.addCell(u.getCorreo());
                table.addCell(u.getTelefono());
            }
            doc.add(table);
            doc.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
}