package com.example.demo.model;


import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer compraId; // PK

    // Relación N:1 con Cliente: Muchas Compras son hechas por un Cliente.
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false) 
    private Cliente cliente;

    //  Relación N:1 con Auto: Muchas Compras son del mismo Auto (Modelo).
    @ManyToOne 
    @JoinColumn(name = "auto_id", nullable = false) // Ya NO es UNIQUE
    private Auto auto;


    @Column(nullable = false)
    private LocalDateTime fechaCompra; // Para fecha y hora

    @Column(nullable = false)
    private Double precioFinal;

    // Atributo de Borrado Lógico
    @Column(nullable = false)
    private boolean estado = true;

    // Constructores, Getters y Setters...

 // Constructor Vacío
    public Compra() {}

    // Constructor con parámetros
    public Compra(Cliente cliente, Auto auto, LocalDateTime fechaCompra, Double precioFinal) {
        this.cliente = cliente;
        this.auto = auto;
        this.fechaCompra = fechaCompra;
        this.precioFinal = precioFinal;
        this.estado = true;
    }

      // Getters y Setters
    public Integer getCompraId() { return compraId; }
    public void setCompraId(Integer compraId) { this.compraId = compraId; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Auto getAuto() { return auto; } // 'Auto' ahora es el MODELO genérico
    public void setAuto(Auto auto) { this.auto = auto; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }
    public Double getPrecioFinal() { return precioFinal; }
    public void setPrecioFinal(Double precioFinal) { this.precioFinal = precioFinal; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}
