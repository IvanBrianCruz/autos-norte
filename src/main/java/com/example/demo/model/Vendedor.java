package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendedorId; // PK

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private LocalDate fechaContratacion;

    @Column
    private Double salario; // Usamos Double o BigDecimal para dinero

    // Relación 1:N con Autos: Un Vendedor tiene muchos Autos asignados.
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    private List<Auto> autosAsignados = new ArrayList<>();

    // Atributo de Borrado Lógico
    @Column(nullable = false)
    private boolean estado = true;

    // Constructores, Getters y Setters...
    
    // Constructor Vacío
    public Vendedor() {}

    // Constructor con parámetros (sin ID, con estado por defecto)
    public Vendedor(String nombre, String apellido, LocalDate fechaContratacion, Double salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.estado = true; // Por defecto activo
    }
    
    // Getters y Setters
    public Integer getVendedorId() { return vendedorId; }
    public void setVendedorId(Integer vendedorId) { this.vendedorId = vendedorId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
    public List<Auto> getAutosAsignados() { return autosAsignados; }
    public void setAutosAsignados(List<Auto> autosAsignados) { this.autosAsignados = autosAsignados; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}