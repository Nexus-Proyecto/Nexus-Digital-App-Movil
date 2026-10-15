package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.nexusdigital.api.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUsername, tvEmail;
    private Button btnLogout;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);

        sessionManager = new SessionManager(this);

        cargarDatosUsuario();

        btnLogout.setOnClickListener(v -> cerrarSesion());
    }

    private void cargarDatosUsuario() {
        if (sessionManager.estaLogueado()) {

            String nombre = sessionManager.obtenerNombre();
            String apellido = sessionManager.obtenerApellido();
            String email = sessionManager.obtenerEmail();
            String rol = sessionManager.obtenerRol();

            String nombreCompleto = (nombre + " " + apellido).trim();

            if (nombreCompleto.isEmpty()) {
                nombreCompleto = "Usuario";
            }

            tvUsername.setText(nombreCompleto);

            if (email != null && !email.isEmpty()) {
                tvEmail.setText(email);
            } else {
                tvEmail.setText("Correo no disponible");
            }

            // Si tu diseño tiene un TextView para el rol,
            // después podemos mostrar "Comprador" o "Vendedor".
        }
    }

    private void cerrarSesion() {
        sessionManager.cerrarSesion();

        Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}