package pe.edu.petnimals.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {

    // --- ENUM INTERNO ---
    // Definimos los 3 roles permitidos directamente aquí adentro
    public enum TipoRol {
        ADMINISTRADOR,
        VENDEDOR,
        CLIENTE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private TipoRol nombre; // Usa el TipoRol que está aquí arriba

    public Rol() {}

    public Long getIdRol() { return idRol; }
    public void setIdRol(Long idRol) { this.idRol = idRol; }
    
    public TipoRol getNombre() { return nombre; }
    public void setNombre(TipoRol nombre) { this.nombre = nombre; }
}