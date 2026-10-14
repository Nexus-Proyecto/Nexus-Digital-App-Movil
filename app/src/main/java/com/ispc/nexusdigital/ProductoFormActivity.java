package com.ispc.nexusdigital;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.nexusdigital.api.ApiClient;
import com.ispc.nexusdigital.api.ProductoRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoFormActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCTO = "producto";

    private EditText etNombre, etDescripcion, etPrecio, etStock;
    private Button btnGuardar;
    private ProgressBar progressBar;
    private TextView tvTitulo, tvError;
    private Producto productoEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_form);

        tvTitulo = findViewById(R.id.tvTituloForm);
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        etStock = findViewById(R.id.etStock);
        tvError = findViewById(R.id.tvErrorForm);
        btnGuardar = findViewById(R.id.btnGuardar);
        progressBar = findViewById(R.id.progressBarForm);

        productoEditar = (Producto) getIntent().getSerializableExtra(EXTRA_PRODUCTO);
        if (productoEditar != null) {
            tvTitulo.setText("Editar producto");
            etNombre.setText(productoEditar.getNombre());
            etDescripcion.setText(productoEditar.getDescripcion());
            etPrecio.setText(String.valueOf(productoEditar.getPrecio()));
            etStock.setText(String.valueOf(productoEditar.getStock()));
        }

        btnGuardar.setOnClickListener(v -> guardar());
    }

    private void guardar() {
        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String precioTxt = etPrecio.getText().toString().trim();
        String stockTxt = etStock.getText().toString().trim();

        tvError.setVisibility(View.GONE);

        if (nombre.isEmpty() || precioTxt.isEmpty()) {
            mostrarError("Completá el nombre y el precio");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTxt);
        } catch (NumberFormatException e) {
            mostrarError("El precio no es válido");
            return;
        }
        if (precio <= 0) {
            mostrarError("El precio debe ser mayor a 0");
            return;
        }

        int stock = 0;
        if (!stockTxt.isEmpty()) {
            try {
                stock = Integer.parseInt(stockTxt);
            } catch (NumberFormatException e) {
                mostrarError("El stock no es válido");
                return;
            }
        }

        ProductoRequest request = new ProductoRequest(nombre, descripcion, precio, stock);
        setLoading(true);

        Call<Producto> call = (productoEditar == null)
                ? ApiClient.getApiService().crearProducto(request)
                : ApiClient.getApiService().actualizarProducto(productoEditar.getIdProducto(), request);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                setLoading(false);
                if (response.isSuccessful()) {
                    Toast.makeText(ProductoFormActivity.this,
                            productoEditar == null ? "Producto creado" : "Producto actualizado",
                            Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    mostrarError(mensajeDeError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                setLoading(false);
                mostrarError("Error de conexión: " + t.getMessage());
            }
        });
    }

    private String mensajeDeError(int codigo) {
        switch (codigo) {
            case 401:
                return "Tu sesión no es válida o venció. Iniciá sesión de nuevo.";
            case 403:
                return "Solo los vendedores pueden publicar o editar productos.";
            case 400:
                return "Revisá los datos ingresados.";
            default:
                return "No se pudo guardar. Código: " + codigo;
        }
    }

    private void setLoading(boolean cargando) {
        progressBar.setVisibility(cargando ? View.VISIBLE : View.GONE);
        btnGuardar.setEnabled(!cargando);
    }

    private void mostrarError(String mensaje) {
        tvError.setText(mensaje);
        tvError.setVisibility(View.VISIBLE);
    }
}

