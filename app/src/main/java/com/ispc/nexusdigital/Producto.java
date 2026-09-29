package com.ispc.nexusdigital;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String vendedor;

    public Producto(int id, String nombre, String descripcion, double precio, int stock, String vendedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.vendedor = vendedor;
    }

    public int getId() {
        return id;
    }

    // Alias para ser compatible con CarritoManager
    public int getIdProducto() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getVendedor() {
        return vendedor;
    }

    public String getNombreVendedor() {
        return vendedor;
    }
}