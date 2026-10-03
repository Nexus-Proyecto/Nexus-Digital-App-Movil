package com.ispc.nexusdigital;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.nexusdigital.api.SessionManager;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 5000;

    private Handler handler;
    private Runnable runnable;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sessionManager = new SessionManager(this);

        ImageView imgLogo = findViewById(R.id.imgLogo);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.fade_in_scale);
        imgLogo.startAnimation(animacion);

        Button btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> {
            if (handler != null && runnable != null) {
                handler.removeCallbacks(runnable);
            }

            verificarSesion();
        });

        handler = new Handler(Looper.getMainLooper());

        runnable = this::verificarSesion;

        handler.postDelayed(runnable, SPLASH_DURATION);
    }

    private void verificarSesion() {

        Intent intent;

        if (sessionManager.estaLogueado()) {
            intent = new Intent(SplashActivity.this, HomeActivity.class);
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