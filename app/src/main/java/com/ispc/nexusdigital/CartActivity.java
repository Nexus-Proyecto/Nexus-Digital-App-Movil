package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCarrito;
    private TextView tvTotal;
    private Button btnSeguirComprando;
    private Button btnComprar;

    private CarritoAdapter adapter;
    private CarritoManager carritoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCarrito = findViewById(R.id.rvCarrito);
        tvTotal = findViewById(R.id.tvTotal);
        btnComprar = findViewById(R.id.btnComprar);
        btnSeguirComprando = findViewById(R.id.btnSeguirComprando);

        carritoManager = CarritoManager.getInstancia();

        rvCarrito.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CarritoAdapter(
                carritoManager.getListaCarrito(),
                this::actualizarTotal
        );

        rvCarrito.setAdapter(adapter);

        actualizarTotal();

        btnSeguirComprando.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        btnComprar.setOnClickListener(v -> {

            if (carritoManager.getListaCarrito().isEmpty()) {
                Toast.makeText(
                        this,
                        "El carrito está vacío",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            carritoManager.vaciarCarrito();
            adapter.notifyDataSetChanged();
            actualizarTotal();

            Toast.makeText(
                    this,
                    "¡Compra confirmada correctamente!",
                    Toast.LENGTH_LONG
            ).show();
        });
    }

    private void actualizarTotal() {
        double total = carritoManager.calcularTotal();

        tvTotal.setText(
                "Total: $ " + String.format("%.2f", total)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
            actualizarTotal();
        }
    }
}