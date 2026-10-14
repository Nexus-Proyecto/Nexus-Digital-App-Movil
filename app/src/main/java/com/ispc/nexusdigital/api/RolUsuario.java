package com.ispc.nexusdigital.api;

import android.content.Context;
import android.util.Base64;

import com.ispc.nexusdigital.Producto;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class RolUsuario {

    // Lee el rol desde el payload del JWT guardado en SessionManager
    public static String obtenerRol(Context context) {
        String token = new SessionManager(context).obtenerToken();
        if (token == null) return null;
        try {
            String[] partes = token.split("\\.");
            if (partes.length < 2) return null;
            byte[] datos = Base64.decode(partes[1],
                    Base64.URL_SAFE | Base64.NO_PADDING | Base64.NO_WRAP);
            JSONObject payload = new JSONObject(new String(datos, StandardCharsets.UTF_8));
            return payload.optString("rol", null);
        } catch (Exception e) {
            return null;
        }
    }

    // Vendedor o administrador pueden publicar productos
    public static boolean puedePublicar(Context context) {
        String rol = obtenerRol(context);
        return "vendedor".equals(rol) || "administrador".equals(rol);
    }

    // Editar/eliminar: el administrador siempre, el vendedor solo sus productos
    public static boolean puedeGestionar(Context context, Producto producto) {
        String rol = obtenerRol(context);
        if ("administrador".equals(rol)) return true;
        return "vendedor".equals(rol)
                && producto != null
                && producto.getIdUsuario() == new SessionManager(context).obtenerIdUsuario();
    }
}