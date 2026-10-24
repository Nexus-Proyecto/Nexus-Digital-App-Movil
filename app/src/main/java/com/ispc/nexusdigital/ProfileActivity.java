package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.nexusdigital.api.SessionManager;
import androidx.appcompat.app.AlertDialog;

import com.ispc.nexusdigital.api.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUsername, tvEmail;
    private Button btnLogout, btnEliminarCuenta;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);

        btnEliminarCuenta = findViewById(R.id.btnEliminarCuenta);

        sessionManager = new SessionManager(this);

        cargarDatosUsuario();

        btnLogout.setOnClickListener(v -> cerrarSesion());
        btnEliminarCuenta.setOnClickListener(v -> mostrarConfirmacionEliminar());
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
    private void mostrarConfirmacionEliminar() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar cuenta")
                .setMessage("¿Seguro que querés eliminar tu cuenta? Esta acción no se puede deshacer.")
                .setPositiveButton("Eliminar", (dialog, which) -> eliminarCuenta())
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void eliminarCuenta() {
        btnEliminarCuenta.setEnabled(false);

        ApiClient.getApiService().eliminarMiCuenta().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    sessionManager.cerrarSesion();
                    Toast.makeText(ProfileActivity.this,
                            "Cuenta eliminada correctamente", Toast.LENGTH_SHORT).show();
                    irALoginLimpiandoPantallas();
                } else {
                    btnEliminarCuenta.setEnabled(true);
                    if (response.code() == 403) {
                        Toast.makeText(ProfileActivity.this,
                                "Esta cuenta no se puede eliminar desde la app",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ProfileActivity.this,
                                "No se pudo eliminar la cuenta. Código: " + response.code(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                btnEliminarCuenta.setEnabled(true);
                Toast.makeText(ProfileActivity.this,
                        "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void irALoginLimpiandoPantallas() {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}