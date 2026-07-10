package pe.edu.petnimals.exportar;

import pe.edu.petnimals.model.Producto;
import java.io.FileWriter;
import java.util.List;
import pe.edu.petnimals.model.Animal;
import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.model.Usuario;

public class ExportarCSV {
    public void productos(String ruta, List<Producto> productos) {
        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("ID,NOMBRE,DESCRIPCION,PRECIO,STOCK\n");
            for (Producto p : productos) {
                writer.write(
                        p.getIdProducto() + "," +
                        p.getNombre() + "," +
                        p.getDescripcion() + "," +
                        p.getPrecio() + "," +
                        p.getStock() + "\n"
                );
            }
            System.out.println("CSV generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en CSV: " + e.getMessage());
        }
    }
    
    public void categorias(String ruta, List<Categoria> categorias) {
        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("ID,NOMBRE\n");
            for (Categoria p : categorias) {
                writer.write(
                        p.getIdCategoria() + "," +
                        p.getNombre() + "\n"
                );
            }
            System.out.println("CSV generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en CSV: " + e.getMessage());
        }
    }
    
    public void usuarios(String ruta, List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("ID,NOMBRES,APELLIDOS,EMAIL,TELEFONO\n");
            for (Usuario p : usuarios) {
                writer.write(
                        p.getIdUsuario() + "," +
                        p.getNombres() + "," +
                        p.getApellidos() + "," +
                        p.getCorreo() + "," +
                        p.getTelefono() + "\n"
                );
            }
            System.out.println("CSV generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en CSV: " + e.getMessage());
        }
    }
    
    public void animales(String ruta, List<Animal> animales) {
        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("ID,NOMBRES,APELLIDOS,EMAIL,TELEFONO\n");
            for (Animal p : animales) {
                writer.write(
                        p.getIdAnimal() + "," +
                        p.getNombre() + "," +
                        p.getEdad() + "," +
                        p.getRaza() + "," +
                        p.getDescripcion() + "\n"
                );
            }
            System.out.println("CSV generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en CSV: " + e.getMessage());
        }
    }
}