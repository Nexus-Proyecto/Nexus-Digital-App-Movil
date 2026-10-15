package com.ispc.nexusdigital.api;

public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    public ProductoRequest(String nombre, String descripcion, double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
}