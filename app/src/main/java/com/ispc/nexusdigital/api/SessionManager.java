package com.ispc.nexusdigital.api;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "nexus_session";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ID_USUARIO = "id_usuario";

    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_APELLIDO = "apellido";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROL = "rol";

    private final SharedPreferences prefs;



    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void guardarToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public String obtenerToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public void guardarIdUsuario(int idUsuario) {
        prefs.edit().putInt(KEY_ID_USUARIO, idUsuario).apply();
    }

    public int obtenerIdUsuario() {
        return prefs.getInt(KEY_ID_USUARIO, -1);
    }

    public void guardarDatosUsuario(String nombre, String apellido, String email, String rol) {
        prefs.edit()
                .putString(KEY_NOMBRE, nombre)
                .putString(KEY_APELLIDO, apellido)
                .putString(KEY_EMAIL, email)
                .putString(KEY_ROL, rol)
                .apply();
    }

    public String obtenerNombre() {
        return prefs.getString(KEY_NOMBRE, "");
    }

    public String obtenerApellido() {
        return prefs.getString(KEY_APELLIDO, "");
    }

    public String obtenerEmail() {
        return prefs.getString(KEY_EMAIL, "");
    }

    public String obtenerRol() {
        return prefs.getString(KEY_ROL, "");
    }

    public boolean estaLogueado() {
        return obtenerToken() != null;
    }

    public void cerrarSesion() {
        prefs.edit()
                .remove(KEY_TOKEN)
                .remove(KEY_ID_USUARIO)
                .remove(KEY_NOMBRE)
                .remove(KEY_APELLIDO)
                .remove(KEY_EMAIL)
                .remove(KEY_ROL)
                .apply();
    }
}
