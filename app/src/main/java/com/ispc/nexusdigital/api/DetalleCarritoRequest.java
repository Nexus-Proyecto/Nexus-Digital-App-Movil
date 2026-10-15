package com.ispc.nexusdigital.api;

public class DetalleCarritoRequest {

    private int id_carrito;
    private int id_producto;
    private int cantidad;

    public DetalleCarritoRequest(int idCarrito, int idProducto, int cantidad) {
        this.id_carrito = idCarrito;
        this.id_producto = idProducto;
        this.cantidad = cantidad;
    }
}
