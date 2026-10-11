package com.ispc.nexusdigital;

import com.ispc.nexusdigital.api.LoginResponse;
import com.ispc.nexusdigital.api.SessionManager;

import retrofit2.Response;

public class LoginResultHandler {

    private final SessionManager sessionManager;

    public LoginResultHandler(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public String procesarRespuesta(Response<LoginResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            sessionManager.guardarToken(response.body().getAccess());
            sessionManager.guardarIdUsuario(response.body().getId_usuario());
            return null;
        }
        return "Email o contraseña incorrectos";
    }

    public String procesarFallo() {
        return "No se pudo conectar con el servidor";
    }
}
