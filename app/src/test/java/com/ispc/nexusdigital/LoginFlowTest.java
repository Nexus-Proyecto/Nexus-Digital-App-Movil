package com.ispc.nexusdigital;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ispc.nexusdigital.api.LoginResponse;
import com.ispc.nexusdigital.api.SessionManager;

import org.junit.Before;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class LoginFlowTest {

    private SessionManager sessionManager;
    private LoginResultHandler loginResultHandler;

    @Before
    public void setUp() {
        sessionManager = mock(SessionManager.class);
        loginResultHandler = new LoginResultHandler(sessionManager);
    }

    // AUT-UNIT-05: Login - inicio exitoso
    @Test
    public void loginExitoso_guardaTokenYUsuario() {
        LoginResponse body = mock(LoginResponse.class);
        when(body.getAccess()).thenReturn("access-token-fake");
        when(body.getId_usuario()).thenReturn(3);

        Response<LoginResponse> response = Response.success(body);

        String error = loginResultHandler.procesarRespuesta(response);

        assertNull(error);
        verify(sessionManager).guardarToken("access-token-fake");
        verify(sessionManager).guardarIdUsuario(3);
    }

    // AUT-UNIT-06: Login - manejo de error (credenciales inválidas)
    @Test
    public void loginConCredencialesInvalidas_noGuardaSesionYDevuelveError() {
        ResponseBody errorBody = ResponseBody.create(
                MediaType.parse("application/json"),
                "{\"error\":\"Credenciales incorrectas\"}"
        );
        Response<LoginResponse> response = Response.error(401, errorBody);

        String error = loginResultHandler.procesarRespuesta(response);

        assertNotNull(error);
        verify(sessionManager, never()).guardarToken(anyString());
        verify(sessionManager, never()).guardarIdUsuario(anyInt());
    }

    // AUT-UNIT-06: Login - manejo de error (sin conexión al servidor)
    @Test
    public void loginSinConexion_devuelveMensajeDeError() {
        String error = loginResultHandler.procesarFallo();

        assertEquals("No se pudo conectar con el servidor", error);
    }
}
