package com.ispc.nexusdigital.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    // Cambiá "api/login/" por la ruta real de tu API de Django
    @Headers("Content-Type: application/json")
    @POST("api/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
