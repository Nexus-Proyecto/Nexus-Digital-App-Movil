package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.nexusdigital.api.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "NexusPrefs";
    private static final String KEY_JWT_TOKEN = "jwt_token";
    private static final int SPLASH_DURATION = 5000;

    private Handler handler;
    private Runnable runnable;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sessionManager = new SessionManager(this);

        Button btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler != null && runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                verificarSesion();
            }

            verificarSesion();
        });

        handler = new Handler(Looper.getMainLooper());

        runnable = this::verificarSesion;

        handler.postDelayed(runnable, SPLASH_DURATION);
    }

    private void verificarSesion() {

        Intent intent;
        if (token != null && !token.trim().isEmpty()) {
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        }

        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}