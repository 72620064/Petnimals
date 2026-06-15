package pe.edu.petnimals.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    // unique = true asegura en la base de datos que NO se repitan correos (Cumple RN-01)
    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, length = 255) // 255 porque luego las contraseñas se encriptan y se hacen largas
    private String contrasenia;

    // Definimos el rol como un texto (CLIENTE, VETERINARIO, ADMINISTRADOR, ALBERGUE) (Cumple RN-06)
    @Column(nullable = false, length = 20)
    private String rol;

    // Constructor vacío obligatorio para Hibernate
    public Usuario() {}

    // Constructor completo
    public Usuario(String nombre, String correo, String contrasenia, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}