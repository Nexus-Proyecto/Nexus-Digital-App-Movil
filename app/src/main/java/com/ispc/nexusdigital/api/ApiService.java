package com.ispc.nexusdigital.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // Cambiá "api/login/" por la ruta real de tu API de Django
    @Headers("Content-Type: application/json")
    @POST("api/auth/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("api/auth/register/")
    Call<Void> register(@Body RegisterRequest registerRequest);

    @Headers("Content-Type: application/json")
    @POST("api/carritos/")
    Call<CarritoResponse> crearCarrito(@Body CarritoRequest carritoRequest);

    @Headers("Content-Type: application/json")
    @POST("api/carritos/{id}/agregar_item/")
    Call<Void> agregarItem(@Path("id") int idCarrito, @Body DetalleCarritoRequest detalleCarritoRequest);

    @POST("api/carritos/{id}/confirmar/")
    Call<OrdenCompraResponse> confirmarCompra(@Path("id") int idCarrito);
}
