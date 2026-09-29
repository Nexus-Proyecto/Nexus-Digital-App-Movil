package com.ispc.nexusdigital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNombre, tvDescripcion, tvPrecio, tvStock, tvVendedor;
    private Button btnAgregarCarrito, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvStock = findViewById(R.id.tvStock);
        tvVendedor = findViewById(R.id.tvVendedor);
        btnAgregarCarrito = findViewById(R.id.btnAgregarCarrito);
        btnVolver = findViewById(R.id.btnVolver);

        // Obtener datos pasados desde el Intent (o valores por defecto del video)
        String nombre = getIntent().getStringExtra("nombre");
        if (nombre == null) nombre = "Notebook Lenovo";
        double precio = getIntent().getDoubleExtra("precio", 350000.0);

        tvNombre.setText(nombre);
        tvPrecio.setText("$ " + precio);

        final String productoFinal = nombre;
        final double precioFinal = precio;

        // Botón "Agregar al carrito"
        btnAgregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar ítem en SharedPreferences para mostrarlo en CartActivity
                SharedPreferences cartPrefs = getSharedPreferences("CartPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = cartPrefs.edit();
                editor.putString("item_nombre", productoFinal);
                editor.putFloat("item_precio", (float) precioFinal);
                editor.putInt("item_cantidad", 1);
                editor.apply();

                Intent intent = new Intent(DetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        // Botón "Volver"
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Regresa a HomeActivity
            }
        });
    }
}