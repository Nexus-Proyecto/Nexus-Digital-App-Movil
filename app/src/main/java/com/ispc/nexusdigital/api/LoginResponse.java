package com.ispc.nexusdigital.api;

public class LoginResponse {

    private int id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private String access;
    private String refresh;
    private String mensaje;

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public String getAccess() {
        return access;
    }

    public String getRefresh() {
        return refresh;
    }

    public String getMensaje() {
        return mensaje;
    }
}
