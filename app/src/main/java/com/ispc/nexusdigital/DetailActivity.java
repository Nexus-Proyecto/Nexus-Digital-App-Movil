package com.ispc.nexusdigital;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvPrecio;
    private TextView tvStock;
    private TextView tvVendedor;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvStock = findViewById(R.id.tvStock);
        tvVendedor = findViewById(R.id.tvVendedor);
        btnVolver = findViewById(R.id.btnVolver);


        String nombre = getIntent().getStringExtra("nombre");
        String descripcion = getIntent().getStringExtra("descripcion");
        double precio = getIntent().getDoubleExtra("precio", 0.0);
        int stock = getIntent().getIntExtra("stock", 0);
        String vendedor = getIntent().getStringExtra("vendedor");


        tvNombre.setText(nombre);
        tvDescripcion.setText(descripcion);
        tvPrecio.setText("$ " + String.format("%.2f", precio));
        tvStock.setText(String.valueOf(stock));
        tvVendedor.setText(vendedor);


        btnVolver.setOnClickListener(v -> finish());
    }
}