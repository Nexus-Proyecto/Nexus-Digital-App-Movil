package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Si por algún motivo el sistema llega a abrir MainActivity,
        // automáticamente lo redirigimos al SplashActivity
        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
        startActivity(intent);
        finish(); // Cierra MainActivity
    }
}