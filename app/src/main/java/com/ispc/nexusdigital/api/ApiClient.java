
package com.ispc.nexusdigital.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // URL utilizada por la aplicación normalmente.
    private static final String BASE_URL = "http://TU_IP:8000/";

    private static Retrofit retrofit;

    public static ApiService getApiService() {
        return getApiService(BASE_URL);
    }

    public static ApiService getApiService(String baseUrl) {
        Retrofit testRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return testRetrofit.create(ApiService.class);
    }
}

