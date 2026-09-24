package com.ispc.nexusdigital;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCarrito;
    private TextView tvTotal;
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

        carritoManager = CarritoManager.getInstancia();

        rvCarrito.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CarritoAdapter(
                carritoManager.getListaCarrito(),
                this::actualizarTotal
        );

        rvCarrito.setAdapter(adapter);

        actualizarTotal();

        btnComprar.setOnClickListener(v -> {
            carritoManager.vaciarCarrito();
            adapter.notifyDataSetChanged();
            actualizarTotal();
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