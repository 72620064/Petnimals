package pe.edu.petnimals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {

    // Lista en memoria para almacenar los artículos temporalmente mientras el servidor corra
    private static final List<Articulo> listaArticulos = new ArrayList<>();

    // Inicializador estático con tus dos primeros posts
    static {
        listaArticulos.add(new Articulo(1, "Vacunas esenciales para tu cachorro", "Salud", 
            "https://images.unsplash.com/photo-1517849845537-4d257902454a?q=80&w=800", 
            "Mantener el esquema al día es crucial para proteger a tu nuevo compañero de enfermedades mortales.", ""));
        
        listaArticulos.add(new Articulo(2, "¿Por qué los gatos maúllan de noche?", "Comportamiento", 
            "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?q=80&w=800", 
            "Descubre las razones detrás de este comportamiento nocturno y cómo ayudar a tu felino a descansar mejor.", ""));
    }

    @GetMapping("/blog")
    public String irAlBlog(Model model) {
        model.addAttribute("articulos", listaArticulos);
        return "blog/blog"; 
    }

    @GetMapping("/blog/nuevo")
    public String nuevoArticuloForm() {
        return "blog/crear";
    }

    @PostMapping("/blog/guardar")
    public String guardarArticulo(@RequestParam("titulo") String titulo,
                                  @RequestParam("categoria") String categoria,
                                  @RequestParam("imagen") String imagen,
                                  @RequestParam("resumen") String resumen,
                                  @RequestParam("contenido") String contenido) {
        
        int nuevoId = listaArticulos.size() + 1;
        listaArticulos.add(new Articulo(nuevoId, titulo, categoria, imagen, resumen, contenido));
        
        return "redirect:/blog";
    }

    @GetMapping("/blog/articulo/{id}")
    public String irAlArticulo(@PathVariable("id") int id, Model model) {
        Articulo articuloEncontrado = listaArticulos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (articuloEncontrado != null) {
            model.addAttribute("titulo", articuloEncontrado.getTitulo());
            model.addAttribute("categoria", articuloEncontrado.getCategoria());
            model.addAttribute("resumen", articuloEncontrado.getResumen());
            model.addAttribute("imagen", articuloEncontrado.getImagen());
            model.addAttribute("contenidoPersonalizado", articuloEncontrado.getContenido());
        }
        
        return "blog/detalle"; 
    }

    // Clase auxiliar interna para manejar el modelo del objeto sin necesidad de una entidad pesada
    public static class Articulo {
        private int id;
        private String titulo;
        private String categoria;
        private String imagen;
        private String resumen;
        private String contenido;

        public Articulo(int id, String titulo, String categoria, String imagen, String resumen, String contenido) {
            this.id = id;
            this.titulo = titulo;
            this.categoria = categoria;
            this.imagen = imagen;
            this.resumen = resumen;
            this.contenido = contenido;
        }

        public int getId() { return id; }
        public String getTitulo() { return titulo; }
        public String getCategoria() { return categoria; }
        public String getImagen() { return imagen; }
        public String getResumen() { return resumen; }
        public String getContenido() { return contenido; }
    }
}