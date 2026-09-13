package com.ispc.nexusdigital.api;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "nexus_session";
    private static final String KEY_TOKEN = "token";

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

    public boolean estaLogueado() {
        return obtenerToken() != null;
    }

    public void cerrarSesion() {
        prefs.edit().remove(KEY_TOKEN).apply();
    }
}
