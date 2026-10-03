package com.ispc.nexusdigital;

import java.util.ArrayList;
import java.util.List;

public class CarritoManager {

    private static CarritoManager instancia;

    private final List<CarritoItem> listaCarrito;

    private CarritoManager() {
        listaCarrito = new ArrayList<>();
    }

    public static CarritoManager getInstancia() {
        if (instancia == null) {
            instancia = new CarritoManager();
        }

        return instancia;
    }

    public void agregarProducto(Producto producto) {

        for (CarritoItem item : listaCarrito) {

            if (item.getProducto().getIdProducto() == producto.getIdProducto()) {

                if (item.getCantidad() < producto.getStock()) {
                    item.setCantidad(item.getCantidad() + 1);
                }

                return;
            }
        }

        if (producto.getStock() > 0) {
            listaCarrito.add(new CarritoItem(producto, 1));
        }
    }

    public void eliminarProducto(Producto producto) {

        for (int i = 0; i < listaCarrito.size(); i++) {

            if (listaCarrito.get(i).getProducto().getIdProducto()
                    == producto.getIdProducto()) {

                listaCarrito.remove(i);
                return;
            }
        }
    }

    public List<CarritoItem> getListaCarrito() {
        return listaCarrito;
    }

    public double calcularTotal() {

        double total = 0;

        for (CarritoItem item : listaCarrito) {
            total += item.getSubtotal();
        }

        return total;
    }

    public void vaciarCarrito() {
        listaCarrito.clear();
    }
}