package com.ispc.nexusdigital;

import java.io.Serializable;

public class Producto implements Serializable {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int idUsuario;
    private String nombreVendedor;

    public Producto(int idProducto, String nombre, String descripcion, double precio, int stock, int idUsuario, String nombreVendedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idUsuario = idUsuario;
        this.nombreVendedor = nombreVendedor;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public int getIdUsuario() { return idUsuario; }
    public String getNombreVendedor() { return nombreVendedor; }
}