package com.example.demo.model;

import java.time.LocalDate;
import java.time.Period;  // Importa la clase para calcular períodos de tiempo 

public class Auto {
    // Atributos
    private String patente;
    private String marca; //ford
    private LocalDate fechaFabricacion;
    private String provinciaOrigen;
   // private boolean estado = true / false;

    // --- Constructores ---

    // Constructor por defecto
    public Auto() {
        // pantente = null
        // marca = null
        // fechaFabricacion = null
        // valor = 0
        // coleccionable = false
    }

    // Constructor parametrizado
    public Auto(String patente, String marca, LocalDate fechaFabricacion, String provinciaOrigen) {
        this.patente = patente;
        this.marca = marca;
        this.fechaFabricacion = fechaFabricacion;
        this.provinciaOrigen = provinciaOrigen;
    }

    // Constructor parcial
    public Auto(String patente, String marca, LocalDate fechaFabricacion) {
        this.patente = patente;
        this.marca = marca;
        this.fechaFabricacion = fechaFabricacion;
        this.provinciaOrigen = "Jujuy";
    }

    // --- Métodos Accesores (Getters y Setters) --- Main 

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca; //retorna la marca
    }

    public void setMarca(String marca) {
        this.marca = marca; //frot modifica la marca uso set ford PARA CREAR Y PARA MODIFICAR
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public String getProvinciaOrigen() {
        return provinciaOrigen;
    }

    public void setProvinciaOrigen(String provinciaOrigen) {
        this.provinciaOrigen = provinciaOrigen;
    }

    // --- Métodos de Lógica de Negocio ---

    /** integer 
     * Calcula la antigüedad del auto en años.
     */
    public int calcularAntiguedad() {
        return Period.between(this.fechaFabricacion, LocalDate.now()).getYears();//01/01/2000 AHORA NOW = 06-09-2025 AÑOS   RESTA 26 INT
    }

    /**
     * Verifica si el auto es un clásico (más de 25 años de antigüedad).
     * @return true si es clásico, false si no.
     */
    public boolean esAutoClasico() { //true o false//  VERDAD O FALSO
        return calcularAntiguedad() > 25; //ES COMO IF ES UN METODO 
        // SI ES MAYOR A 25 RETORNA TRUE SINO FALSE 😡
        // 26 AÑOS SI ES UN AUTO CLASICO🟩 TRUe / Verdadero
    }

    /**
     * Muestra todos los datos del auto, incluyendo su antigüedad y si es un clásico.
     */
    public void mostrarDatos() { // metodo que imprime todos los datos del auto
        System.out.println("--- Datos del Auto ---");
        System.out.println("Patente: " + this.patente);
        System.out.println("Marca: " + this.marca);
        System.out.println("Fecha de Fabricación: " + this.fechaFabricacion);
        System.out.println("Provincia de Origen: " + this.provinciaOrigen);
        System.out.println("Antigüedad: " + calcularAntiguedad() + " años"); //26 AÑOS

        if (esAutoClasico()) { // 26 AÑOS true
            System.out.println("Es un auto clásico."); // mayor de edad
        } else {
            System.out.println("No es un auto clásico.");// menor de edad
        }
    }
}