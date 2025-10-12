package com.example.demo.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer autoId; // PK

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer anio; // Usamos Integer para el año

    @Column(nullable = false)
    private Double precio;

    @Column
    private String color;

     // Relación N:1 con Vendedor: Muchos Modelos son asignados a un Vendedor.
    @ManyToOne
    @JoinColumn(name = "vendedor_id") // FK
    private Vendedor vendedor;
    
    // Relación 1:N con Compra: Un Modelo puede ser vendido (comprado) muchas veces.
    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL)
    private List<Compra> comprasRealizadas = new ArrayList<>(); // Corregido el nombre para claridad


    // Atributo de Borrado Lógico
    @Column(nullable = false)
    private boolean estado = true;

    // Constructores, Getters y Setters...

    // Constructor Vacío
    public Auto() {}

    // Constructor con parámetros (sin relaciones)
    public Auto(String marca, String modelo, Integer anio, Double precio, String color, Vendedor vendedor) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.color = color;
        this.vendedor = vendedor;
        this.estado = true;
    }
    
    // Getters y Setters
    public Integer getAutoId() { return autoId; }
    public void setAutoId(Integer autoId) { this.autoId = autoId; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Vendedor getVendedor() { return vendedor; }
    public void setVendedor(Vendedor vendedor) { this.vendedor = vendedor; }
    public List<Compra> getComprasRealizadas() { return comprasRealizadas; }
    public void setComprasRealizadas(List<Compra> comprasRealizadas) { this.comprasRealizadas = comprasRealizadas; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}