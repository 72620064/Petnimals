package pe.edu.petnimals.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    // --- ENUM INTERNO ---
    // Definimos las 3 únicas posibilidades permitidas por el profesor
    public enum EstadoPedido {
        PENDIENTE,
        PROCESADO,
        ENTREGADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "fecha_pedido", insertable = false, updatable = false)
    private LocalDateTime fechaPedido;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING) // Guarda el estado como texto ("PENDIENTE", etc.) en MySQL
    @Column(length = 50)
    private EstadoPedido estado; // Cambiado de String a EstadoPedido

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Pedido() {}

    // Getters y Setters
    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public LocalDateTime getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    // Getters y Setters actualizados para usar el Enum Interno
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}