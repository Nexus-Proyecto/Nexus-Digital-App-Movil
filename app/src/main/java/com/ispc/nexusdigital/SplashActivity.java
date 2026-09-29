package com.ispc.nexusdigital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "NexusPrefs";
    private static final String KEY_JWT_TOKEN = "jwt_token";
    private static final int SPLASH_DURATION = 2000; // 2 segundos de pantalla de carga

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button btnIngresar = findViewById(R.id.btnIngresar);

        // Si el usuario hace clic en el botón de ingreso, se cancela el temporizador y verificamos
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler != null && runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                verificarSesion();
            }
        });

        // Espera 2 segundos de pantalla de carga y verifica automáticamente el token
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                verificarSesion();
            }
        };
        handler.postDelayed(runnable, SPLASH_DURATION);
    }

    /**
     * Valida si existe un token JWT en SharedPreferences
     */
    private void verificarSesion() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String token = preferences.getString(KEY_JWT_TOKEN, null);

        Intent intent;
        if (token != null && !token.trim().isEmpty()) {
            // Token JWT -> Sesión activa -> se dirige al Home
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            // Sin token
            // Se envía a MainActivity por ahora
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }

        startActivity(intent);
        finish(); // Cierra el Splash para que no vuelva al presionar atrás
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Evita fugas de memoria si la activity se destruye antes de que corra el temporizador
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}