package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ispc.nexusdigital.api.ApiClient;
import com.ispc.nexusdigital.api.RolUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvPrecio;
    private TextView tvStock;
    private TextView tvVendedor;

    private Button btnAgregarCarrito;
    private Button btnVolver;
    private Button btnEditar;
    private Button btnEliminar;

    private Producto producto;

    // Si el formulario guardó cambios, se cierra el detalle y el listado se recarga solo
    private final ActivityResultLauncher<Intent> editarLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    finish();
                }
            });

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
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

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

            if (RolUsuario.puedeGestionar(this, producto)) {
                btnEditar.setVisibility(View.VISIBLE);
                btnEliminar.setVisibility(View.VISIBLE);
            }

            btnEditar.setOnClickListener(v -> {
                Intent intent = new Intent(DetailActivity.this, ProductoFormActivity.class);
                intent.putExtra(ProductoFormActivity.EXTRA_PRODUCTO, producto);
                editarLauncher.launch(intent);
            });

            btnEliminar.setOnClickListener(v -> confirmarEliminar());

        }

        btnVolver.setOnClickListener(v -> finish());
    }

    private void confirmarEliminar() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar producto")
                .setMessage("¿Seguro que querés eliminar \"" + producto.getNombre()
                        + "\"? Esta acción no se puede deshacer.")
                .setPositiveButton("Eliminar", (dialog, which) -> eliminarProducto())
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void eliminarProducto() {
        btnEliminar.setEnabled(false);

        ApiClient.getApiService().eliminarProducto(producto.getIdProducto())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        btnEliminar.setEnabled(true);
                        if (response.isSuccessful()) {
                            Toast.makeText(DetailActivity.this,
                                    "Producto eliminado", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DetailActivity.this,
                                    mensajeDeError(response.code()), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        btnEliminar.setEnabled(true);
                        Toast.makeText(DetailActivity.this,
                                "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private String mensajeDeError(int codigo) {
        switch (codigo) {
            case 401:
                return "Tu sesión no es válida o venció. Iniciá sesión de nuevo.";
            case 403:
                return "No tenés permiso para eliminar este producto.";
            case 404:
                return "El producto ya no existe.";
            default:
                return "No se pudo eliminar. Código: " + codigo;
        }
    }
}