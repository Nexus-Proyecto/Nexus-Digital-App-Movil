package com.ispc.nexusdigital.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // Reemplazá esta URL por la de tu servidor Django.
    // Si corrés Django localmente y probás desde el emulador de Android Studio,
    // usá 10.0.2.2 en vez de 127.0.0.1 (el emulador lo redirige a tu PC).
    private static final String BASE_URL = "http://10.0.2.2:8000/";

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
