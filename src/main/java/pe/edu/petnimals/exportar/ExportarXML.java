package pe.edu.petnimals.exportar;

import pe.edu.petnimals.model.Producto;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
import java.util.List;
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
                
                Element precio = doc.createElement("precio");
                precio.setTextContent(String.valueOf(p.getPrecio()));
                
                Element stock = doc.createElement("stock");
                stock.setTextContent(String.valueOf(p.getStock()));
                
                prod.appendChild(id);
                prod.appendChild(nombre);
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
                Element prod = doc.createElement("categoria");
                
                Element id = doc.createElement("Id");
                id.setTextContent(String.valueOf(p.getIdCategoria()));
                
                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(p.getNombre());
                
                prod.appendChild(id);
                prod.appendChild(nombre);
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
                Element prod = doc.createElement("usuario");
                
                Element id = doc.createElement("Id");
                id.setTextContent(String.valueOf(p.getIdUsuario()));
                
                Element nombre = doc.createElement("nombres");
                nombre.setTextContent(p.getNombres());
                
                Element apellido = doc.createElement("apellidos");
                apellido.setTextContent(p.getApellidos());
                
                Element email = doc.createElement("email");
                email.setTextContent(p.getCorreo());
                
                Element telefono = doc.createElement("telefono");
                telefono.setTextContent(String.valueOf(p.getTelefono()));
                
                prod.appendChild(id);
                prod.appendChild(nombre);
                prod.appendChild(apellido);
                prod.appendChild(email);
                root.appendChild(telefono);
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