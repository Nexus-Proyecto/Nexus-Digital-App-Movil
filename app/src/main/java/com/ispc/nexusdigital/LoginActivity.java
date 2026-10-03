package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.nexusdigital.api.ApiClient;
import com.ispc.nexusdigital.api.LoginRequest;
import com.ispc.nexusdigital.api.LoginResponse;
import com.ispc.nexusdigital.api.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final boolean MODO_SIMULADO = true;
    private static final String USUARIO_PRUEBA = "admin";
    private static final String CLAVE_PRUEBA = "1234";

    private EditText etUsuario, etClave;
    private Button btnLogin;
    private ProgressBar progressBar;
    private TextView tvError, tvRegistrarse;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        tvError = findViewById(R.id.tvError);
        tvRegistrarse = findViewById(R.id.tvRegistrarse);
        sessionManager = new SessionManager(this);

        if (sessionManager.estaLogueado()) {
            irAPantallaPrincipal();
            return;
        }

        btnLogin.setOnClickListener(v -> intentarLogin());

        tvRegistrarse.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void intentarLogin() {
        String usuario = etUsuario.getText().toString().trim();
        String clave = etClave.getText().toString().trim();

        tvError.setVisibility(View.GONE);

        if (usuario.isEmpty() || clave.isEmpty()) {
            mostrarError("Completá usuario y contraseña");
            return;
        }

        mostrarCargando(true);

        if (MODO_SIMULADO) {
            loginSimulado(usuario, clave);
            return;
        }

        LoginRequest request = new LoginRequest(usuario, clave);

        ApiClient.getApiService().login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                mostrarCargando(false);

                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    sessionManager.guardarToken(token);
                    irAPantallaPrincipal();
                } else {
                    mostrarError("Usuario o contraseña incorrectos");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mostrarCargando(false);
                mostrarError("No se pudo conectar con el servidor");
            }
        });
    }

    private void loginSimulado(String usuario, String clave) {
        btnLogin.postDelayed(() -> {
            mostrarCargando(false);

            if (usuario.equals(USUARIO_PRUEBA) && clave.equals(CLAVE_PRUEBA)) {
                sessionManager.guardarToken("token-simulado-123");
                irAPantallaPrincipal();
            } else {
                mostrarError("Usuario o contraseña incorrectos");
            }
        }, 800);
    }

    private void mostrarCargando(boolean cargando) {
        progressBar.setVisibility(cargando ? View.VISIBLE : View.GONE);
        btnLogin.setEnabled(!cargando);
    }

    private void mostrarError(String mensaje) {
        tvError.setText(mensaje);
        tvError.setVisibility(View.VISIBLE);
    }

    private void irAPantallaPrincipal() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}