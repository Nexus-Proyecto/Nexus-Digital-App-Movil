package com.ispc.nexusdigital.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // Reemplazá TU_IP por la dirección IP local de la PC donde se ejecuta Django.
// Ejemplo: http://19X.1XX.X.X2:8000/
    private static final String BASE_URL = "http://TU_IP:8000/";

    private static Retrofit retrofit;

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
