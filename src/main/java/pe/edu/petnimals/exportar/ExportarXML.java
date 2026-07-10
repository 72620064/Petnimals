package pe.edu.petnimals.exportar;

import pe.edu.petnimals.model.Producto;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
import java.util.List;
import pe.edu.petnimals.model.Animal;
import pe.edu.petnimals.model.Categoria;
import pe.edu.petnimals.model.Usuario;

public class ExportarXML {
    public void productos(String ruta, List<Producto> productos) {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            org.w3c.dom.Document doc = b.newDocument();
            
            Element root = doc.createElement("productos");
            doc.appendChild(root);
            
            for (Producto p : productos) {
                Element prod = doc.createElement("producto");
                
                Element id = doc.createElement("Id");
                id.setTextContent(String.valueOf(p.getIdProducto()));
                
                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(p.getNombre());
                
                Element descripcion = doc.createElement("descripçion");
                descripcion.setTextContent(p.getDescripcion ());
                
                Element precio = doc.createElement("precio");
                precio.setTextContent(String.valueOf(p.getPrecio()));
                
                Element stock = doc.createElement("stock");
                stock.setTextContent(String.valueOf(p.getStock()));
                
                prod.appendChild(id);
                prod.appendChild(nombre);
                prod.appendChild(descripcion);
                prod.appendChild(precio);
                prod.appendChild(stock);
                root.appendChild(prod);
            }
            
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
            System.out.println("XML generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en XML: " + e.getMessage());
        }
    }
    
     public void categorias(String ruta, List<Categoria> categorias) {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            org.w3c.dom.Document doc = b.newDocument();
            
            Element root = doc.createElement("categorias");
            doc.appendChild(root);
            
            for (Categoria p : categorias) {
                Element cat = doc.createElement("categorias");
                
                Element id = doc.createElement("Id");
                id.setTextContent(String.valueOf(p.getIdCategoria()));
                
                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(p.getNombre());
                
                cat.appendChild(id);
                cat.appendChild(nombre);
                root.appendChild(cat);

            }
            
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
            System.out.println("XML generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en XML: " + e.getMessage());
        }
    }
     
    public void usuarios(String ruta, List<Usuario> usuarios) {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            org.w3c.dom.Document doc = b.newDocument();
            
            Element root = doc.createElement("usuarios");
            doc.appendChild(root);
            
            for (Usuario p : usuarios) {
                Element user = doc.createElement("usuario");
                
                Element id = doc.createElement("Id");
                id.setTextContent(String.valueOf(p.getIdUsuario()));
                
                Element nombres = doc.createElement("nombres");
                nombres.setTextContent(p.getNombres());
                
                Element apellidos = doc.createElement("apellidos");
                apellidos.setTextContent(p.getApellidos());
                
                Element correo = doc.createElement("email");
                correo.setTextContent(p.getCorreo());
                
                Element telefono = doc.createElement("telefono");
                telefono.setTextContent(String.valueOf(p.getTelefono()));
                
                user.appendChild(id);
                user.appendChild(nombres);
                user.appendChild(apellidos);
                user.appendChild(correo);
                user.appendChild(telefono);
                root.appendChild(user);
            }
            
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
            System.out.println("XML generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en XML: " + e.getMessage());
        }
    }
    public void animales(String ruta, List<Animal> animales) {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            org.w3c.dom.Document doc = b.newDocument();
            
            Element root = doc.createElement("animales");
            doc.appendChild(root);
            
            for (Animal p : animales) {
                Element animal = doc.createElement("animales");
                
                Element id = doc.createElement("Id");
                id.setTextContent(String.valueOf(p.getIdAnimal()));
                
                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(p.getNombre());
                
                Element edad = doc.createElement("edad");
                edad.setTextContent(p.getEdad());
                
                Element raza = doc.createElement("raza");
                raza.setTextContent(p.getRaza());
                
                Element descripcion = doc.createElement("descripcion");
                descripcion.setTextContent(String.valueOf(p.getDescripcion ()));
                
                animal.appendChild(id);
                animal.appendChild(nombre);
                animal.appendChild(edad);
                animal.appendChild(raza);
                animal.appendChild(descripcion);
                root.appendChild(animal);
            }
            
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
            System.out.println("XML generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error en XML: " + e.getMessage());
        }
    }
}