package com.ispc.nexusdigital.api;

public class OrdenCompraResponse {

    private int id_orden;
    private int id_usuario;
    private String usuario_nombre;
    private String fecha;
    private double total;

    public int getId_orden() {
        return id_orden;
    }

    public String getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }
}
