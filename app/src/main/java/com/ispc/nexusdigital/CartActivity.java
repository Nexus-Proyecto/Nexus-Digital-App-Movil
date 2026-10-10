package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ispc.nexusdigital.api.ApiClient;
import com.ispc.nexusdigital.api.CarritoRequest;
import com.ispc.nexusdigital.api.CarritoResponse;
import com.ispc.nexusdigital.api.DetalleCarritoRequest;
import com.ispc.nexusdigital.api.ErrorResponse;
import com.ispc.nexusdigital.api.OrdenCompraResponse;
import com.ispc.nexusdigital.api.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCarrito;
    private TextView tvTotal;
    private Button btnSeguirComprando;
    private Button btnComprar;

    private CarritoAdapter adapter;
    private CarritoManager carritoManager;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCarrito = findViewById(R.id.rvCarrito);
        tvTotal = findViewById(R.id.tvTotal);
        btnComprar = findViewById(R.id.btnComprar);
        btnSeguirComprando = findViewById(R.id.btnSeguirComprando);

        carritoManager = CarritoManager.getInstancia();
        sessionManager = new SessionManager(this);

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

            confirmarCompra();
        });
    }

    private void confirmarCompra() {
        mostrarProcesando(true);

        int idUsuario = sessionManager.obtenerIdUsuario();
        CarritoRequest carritoRequest = new CarritoRequest(idUsuario);

        ApiClient.getApiService().crearCarrito(carritoRequest).enqueue(new Callback<CarritoResponse>() {
            @Override
            public void onResponse(Call<CarritoResponse> call, Response<CarritoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int idCarrito = response.body().getId_carrito();
                    agregarItems(idCarrito, 0);
                } else {
                    mostrarProcesando(false);
                    mostrarError("No se pudo crear el carrito en el servidor.");
                }
            }

            @Override
            public void onFailure(Call<CarritoResponse> call, Throwable t) {
                mostrarProcesando(false);
                mostrarError("No se pudo conectar con el servidor.");
            }
        });
    }

    private void agregarItems(int idCarrito, int indice) {
        List<CarritoItem> items = carritoManager.getListaCarrito();

        if (indice >= items.size()) {
            ApiClient.getApiService().confirmarCompra(idCarrito).enqueue(new Callback<OrdenCompraResponse>() {
                @Override
                public void onResponse(Call<OrdenCompraResponse> call, Response<OrdenCompraResponse> response) {
                    mostrarProcesando(false);

                    if (response.isSuccessful() && response.body() != null) {
                        OrdenCompraResponse orden = response.body();

                        carritoManager.vaciarCarrito();
                        adapter.notifyDataSetChanged();
                        actualizarTotal();

                        Toast.makeText(
                                CartActivity.this,
                                "¡Compra confirmada! Orden #" + orden.getId_orden()
                                        + " - Total: $ " + String.format("%.2f", orden.getTotal()),
                                Toast.LENGTH_LONG
                        ).show();
                    } else {
                        mostrarError(obtenerMensajeError(response));
                    }
                }

                @Override
                public void onFailure(Call<OrdenCompraResponse> call, Throwable t) {
                    mostrarProcesando(false);
                    mostrarError("No se pudo conectar con el servidor.");
                }
            });
            return;
        }

        CarritoItem item = items.get(indice);
        DetalleCarritoRequest detalle = new DetalleCarritoRequest(
                idCarrito,
                item.getProducto().getIdProducto(),
                item.getCantidad()
        );

        ApiClient.getApiService().agregarItem(idCarrito, detalle).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    agregarItems(idCarrito, indice + 1);
                } else {
                    mostrarProcesando(false);
                    mostrarError("No se pudo agregar '" + item.getProducto().getNombre() + "' al carrito del servidor.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mostrarProcesando(false);
                mostrarError("No se pudo conectar con el servidor.");
            }
        });
    }

    private String obtenerMensajeError(Response<?> response) {
        try {
            if (response.errorBody() != null) {
                ErrorResponse error = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                if (error != null && error.getDetail() != null) {
                    return error.getDetail();
                }
            }
        } catch (Exception e) {
            // Ignoramos el error de parseo y devolvemos el mensaje genérico de abajo.
        }
        return "No se pudo confirmar la compra.";
    }

    private void mostrarProcesando(boolean procesando) {
        btnComprar.setEnabled(!procesando);
        btnComprar.setText(procesando ? "Procesando..." : "Confirmar compra");
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
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