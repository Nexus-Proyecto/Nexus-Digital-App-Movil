package com.ispc.nexusdigital;

import android.content.Intent;
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

    private Button btnAgregarCarrito;
    private Button btnVolver;

    private Producto producto;

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

        producto = (Producto) getIntent().getSerializableExtra("producto");

        if (producto != null) {

            tvNombre.setText(producto.getNombre());
            tvDescripcion.setText(producto.getDescripcion());
            tvPrecio.setText("$ " + String.format("%.2f", producto.getPrecio()));
            tvStock.setText(String.valueOf(producto.getStock()));
            tvVendedor.setText(producto.getNombreVendedor());

            btnAgregarCarrito.setOnClickListener(v -> {

                CarritoManager.getInstancia().agregarProducto(producto);

                Intent intent = new Intent(DetailActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }

        btnVolver.setOnClickListener(v -> finish());
    }
}