package com.ispc.nexusdigital.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Response;

public class LoginRepositoryNetTest {

    private MockWebServer server;
    private LoginRepository repository;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        server.start();

        // La app apunta al servidor falso, nunca a la red real
        ApiService apiService = ApiClient.getApiService(server.url("/").toString());
        repository = new LoginRepository(apiService);
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    // AUT-NET-01: respuesta 200, el JSON se convierte en el modelo
    @Test
    public void respuesta200_convierteElJsonEnLoginResponse() throws Exception {
        // Arrange
        String json = "{"
                + "\"id_usuario\": 7,"
                + "\"nombre\": \"Ana\","
                + "\"apellido\": \"Perez\","
                + "\"email\": \"ana@mail.com\","
                + "\"rol\": \"comprador\","
                + "\"access\": \"token-acceso\","
                + "\"refresh\": \"token-refresco\","
                + "\"mensaje\": \"Login exitoso\""
                + "}";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(json));

        // Act
        Response<LoginResponse> response =
                repository.login(new LoginRequest("ana@mail.com", "Clave123!")).execute();
        RecordedRequest request = server.takeRequest();

        // Assert
        assertTrue(response.isSuccessful());
        LoginResponse body = response.body();
        assertNotNull(body);
        assertEquals(7, body.getId_usuario());
        assertEquals("ana@mail.com", body.getEmail());
        assertEquals("comprador", body.getRol());
        assertEquals("token-acceso", body.getAccess());
        assertEquals("token-refresco", body.getRefresh());
        assertEquals("POST", request.getMethod());
        assertEquals("/api/auth/login/", request.getPath());
    }

    // AUT-NET-02: respuesta 500, la app maneja el error sin romperse
    @Test
    public void respuesta500_devuelveErrorSinRomperLaApp() throws Exception {
        // Arrange
        server.enqueue(new MockResponse()
                .setResponseCode(500)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"detail\": \"Error interno del servidor\"}"));

        // Act
        Response<LoginResponse> response =
                repository.login(new LoginRequest("ana@mail.com", "Clave123!")).execute();

        // Assert
        assertFalse(response.isSuccessful());
        assertEquals(500, response.code());
        assertNull(response.body());
        ErrorResponse error = new Gson()
                .fromJson(response.errorBody().string(), ErrorResponse.class);
        assertEquals("Error interno del servidor", error.getDetail());
    }
}