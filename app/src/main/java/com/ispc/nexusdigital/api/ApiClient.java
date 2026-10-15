
package com.ispc.nexusdigital.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiClient {

    // URL utilizada por la aplicación normalmente.
    private static final String BASE_URL = "http://TU_IP:8000/";

    private static Retrofit retrofit;
    private static Context appContext;

    // Se llama una vez desde HomeActivity para poder leer el token guardado
    public static void init(Context context) {
        appContext = context.getApplicationContext();
    }

    public static ApiService getApiService() {
        return getApiService(BASE_URL);
    }

    public static ApiService getApiService(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    if (appContext != null) {
                        String token = new SessionManager(appContext).obtenerToken();
                        if (token != null) {
                            builder.header("Authorization", "Bearer " + token);
                        }
                    }
                    return chain.proceed(builder.build());
                })
                .build();
        Retrofit testRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return testRetrofit.create(ApiService.class);
    }
}

