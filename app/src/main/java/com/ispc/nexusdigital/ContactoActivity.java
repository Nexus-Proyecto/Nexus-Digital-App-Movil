package com.ispc.nexusdigital;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContactoActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etMensaje;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        etNombre = findViewById(R.id.etNombreContacto);
        etEmail = findViewById(R.id.etEmailContacto);
        etMensaje = findViewById(R.id.etMensajeContacto);
        btnEnviar = findViewById(R.id.btnEnviarContacto);

        btnEnviar.setOnClickListener(v -> enviarConsulta());
    }

    private void enviarConsulta() {
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String mensaje = etMensaje.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty() || mensaje.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simulación de envío exitoso
        Toast.makeText(this, "¡Mensaje enviado con éxito! Nos contactaremos pronto.", Toast.LENGTH_LONG).show();
        finish(); // Vuelve a la pantalla anterior
    }
}
