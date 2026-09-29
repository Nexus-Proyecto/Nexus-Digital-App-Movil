package com.ispc.nexusdigital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private TextView tvItemNombre, tvItemPrecio, tvCantidad, tvTotal;
    private Button btnEliminar, btnConfirmarCompra, btnVolver;
    private LinearLayout layoutCartItem;

    private SharedPreferences cartPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mi Carrito");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Vinculación con los IDs reales de activity_cart.xml
        tvItemNombre = findViewById(R.id.tvItemNombre);
        tvItemPrecio = findViewById(R.id.tvItemPrecio);
        tvCantidad = findViewById(R.id.tvCantidad);
        tvTotal = findViewById(R.id.tvTotal);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnConfirmarCompra = findViewById(R.id.btnConfirmarCompra);
        btnVolver = findViewById(R.id.btnVolver);
        layoutCartItem = findViewById(R.id.layoutCartItem);

        cartPrefs = getSharedPreferences("CartPrefs", MODE_PRIVATE);

        cargarCarrito();

        // Botón Eliminar ítem
        if (btnEliminar != null) {
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartPrefs.edit().clear().apply();
                    cargarCarrito();
                }
            });
        }

        // 1. Botón "Confirmar compra" -> Lanza Toast, limpia carrito y vuelve a Productos
        if (btnConfirmarCompra != null) {
            btnConfirmarCompra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CartActivity.this, "¡Compra realizada con éxito!", Toast.LENGTH_SHORT).show();
                    cartPrefs.edit().clear().apply();

                    Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            });
        }

        // 2. Botón "Volver" -> Retorna a la pantalla de Productos
        if (btnVolver != null) {
            btnVolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void cargarCarrito() {
        String nombre = cartPrefs.getString("item_nombre", null);
        float precio = cartPrefs.getFloat("item_precio", 0.0f);
        int cantidad = cartPrefs.getInt("item_cantidad", 0);

        if (nombre != null && cantidad > 0) {
            if (layoutCartItem != null) layoutCartItem.setVisibility(View.VISIBLE);
            if (tvItemNombre != null) tvItemNombre.setText(nombre);
            if (tvItemPrecio != null) tvItemPrecio.setText("$ " + precio);
            if (tvCantidad != null) tvCantidad.setText(String.valueOf(cantidad));
            if (tvTotal != null) tvTotal.setText(String.format("Total: $ %.2f", (precio * cantidad)));
            if (btnConfirmarCompra != null) btnConfirmarCompra.setEnabled(true);
        } else {
            if (layoutCartItem != null) layoutCartItem.setVisibility(View.GONE);
            if (tvTotal != null) tvTotal.setText("Total: $ 0.00");
            if (btnConfirmarCompra != null) btnConfirmarCompra.setEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}