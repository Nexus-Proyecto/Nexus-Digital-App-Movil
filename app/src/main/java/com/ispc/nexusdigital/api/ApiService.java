package com.ispc.nexusdigital.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    // Cambiá "api/login/" por la ruta real de tu API de Django
    @Headers("Content-Type: application/json")
    @POST("api/auth/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("api/auth/register/")
    Call<Void> register(@Body RegisterRequest registerRequest);
}
