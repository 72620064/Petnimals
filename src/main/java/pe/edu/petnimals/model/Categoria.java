package pe.edu.petnimals.model; 

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @Column(nullable = false, length = 50)
    private String nombre;

    // Constructor vacío (Obligatorio para Hibernate)
    public Categoria() {}

    // Constructor con parámetros (Opcional, pero útil)
    public Categoria(String nombre) {
        this.nombre = nombre;
        
    }

    // Getters y Setters (Obligatorios)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}