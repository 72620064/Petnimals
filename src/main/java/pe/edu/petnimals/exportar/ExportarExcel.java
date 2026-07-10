package pe.edu.petnimals.exportar;

import pe.edu.petnimals.model.Producto;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.FileOutputStream;
import java.util.List;
import pe.edu.petnimals.model.Animal;
import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.model.Usuario;

public class ExportarExcel {
    public void productos(String ruta, List<Producto> productos) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Productos");
            
            int fila = 0;
            var row0 = sheet.createRow(fila++);
            row0.createCell(0).setCellValue("ID");
            row0.createCell(1).setCellValue("NOMBRE");
            row0.createCell(2).setCellValue("DESCRIPCION");
            row0.createCell(3).setCellValue("PRECIO");
            row0.createCell(4).setCellValue("STOCK");
            
            for (Producto p : productos) {
                var row = sheet.createRow(fila++);
                row.createCell(0).setCellValue(p.getIdProducto());
                row.createCell(1).setCellValue(p.getNombre());
                row.createCell(2).setCellValue(p.getDescripcion());
                row.createCell(3).setCellValue(p.getPrecio() != null ? p.getPrecio().doubleValue() : 0.0);
                row.createCell(4).setCellValue(p.getStock());
            }
            
            FileOutputStream out = new FileOutputStream(ruta);
            wb.write(out);
            out.close();
            wb.close();
            System.out.println("Excel generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en Excel: " + e.getMessage());
        }
    }
    
    public void categorias(String ruta, List<Categoria> categorias) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Categorias");
            
            int fila = 0;
            var row0 = sheet.createRow(fila++);
            row0.createCell(0).setCellValue("ID");
            row0.createCell(1).setCellValue("NOMBRE");
            
            for (Categoria p : categorias) {
                var row = sheet.createRow(fila++);
                row.createCell(0).setCellValue(p.getIdCategoria());
                row.createCell(1).setCellValue(p.getNombre());
            }
            
            FileOutputStream out = new FileOutputStream(ruta);
            wb.write(out);
            out.close();
            wb.close();
            System.out.println("Excel generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en Excel: " + e.getMessage());
        }
    }
    
    public void usuarios(String ruta, List<Usuario> usuarios) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Usuarios");
            
            int fila = 0;
            var row0 = sheet.createRow(fila++);
            row0.createCell(0).setCellValue("ID");
            row0.createCell(1).setCellValue("NOMBRES");
            row0.createCell(2).setCellValue("APELLIDOS");
            row0.createCell(3).setCellValue("EMAIL");
            row0.createCell(4).setCellValue("TELEFONO");
            
            for (Usuario p : usuarios) {
                var row = sheet.createRow(fila++);
                row.createCell(0).setCellValue(p.getIdUsuario());
                row.createCell(1).setCellValue(p.getNombres());
                row.createCell(2).setCellValue(p.getApellidos());
                row.createCell(3).setCellValue(p.getCorreo());
                row.createCell(4).setCellValue(p.getTelefono());
            }
            
            FileOutputStream out = new FileOutputStream(ruta);
            wb.write(out);
            out.close();
            wb.close();
            System.out.println("Excel generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en Excel: " + e.getMessage());
        }
    }
    
    public void animales(String ruta, List<Animal> animales) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Animales");
            
            int fila = 0;
            var row0 = sheet.createRow(fila++);
            row0.createCell(0).setCellValue("ID");
            row0.createCell(1).setCellValue("NOMBRE");
            row0.createCell(2).setCellValue("EDAD");
            row0.createCell(3).setCellValue("RAZA");
            row0.createCell(4).setCellValue("DESCRIPCION");
            
            for (Animal p : animales) {
                var row = sheet.createRow(fila++);
                row.createCell(0).setCellValue(p.getIdAnimal());
                row.createCell(1).setCellValue(p.getNombre());
                row.createCell(2).setCellValue(p.getEdad());
                row.createCell(3).setCellValue(p.getRaza());
                row.createCell(4).setCellValue(p.getDescripcion());
            }
            
            FileOutputStream out = new FileOutputStream(ruta);
            wb.write(out);
            out.close();
            wb.close();
            System.out.println("Excel generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en Excel: " + e.getMessage());
        }
    }
}