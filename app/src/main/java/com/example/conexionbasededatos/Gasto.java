package com.example.conexionbasededatos;

public class Gasto {
    private String nombre;
    private String tipo;
    private double cantidad;
    private String fecha;

    public Gasto(String nombre, String tipo, double cantidad, String fecha) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }
}
