package com.ispc.nexusdigital.api;

// Ajustá los nombres de estos campos según lo que devuelva realmente tu API de Django.
// Ejemplo típico con Django REST Framework (TokenAuthentication): {"token": "abc123..."}
public class LoginResponse {
    private String token;

    public String getToken() {
        return token;
    }
}
