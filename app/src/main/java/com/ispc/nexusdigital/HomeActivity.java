package com.ispc.nexusdigital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private static final String PREFS_NAME = "NexusPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Configuración e integración de la Toolbar como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Productos");
            }
        }

        recyclerViewProductos = findViewById(R.id.recyclerViewProductos);

        if (recyclerViewProductos != null) {
            recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));

            List<Producto> listaProductos = obtenerProductosDemo();

            ProductoAdapter adapter = new ProductoAdapter(this, listaProductos, new ProductoAdapter.OnProductoClickListener() {
                @Override
                public void onProductoClick(Producto producto) {
                    Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                    intent.putExtra("id", producto.getId());
                    intent.putExtra("nombre", producto.getNombre());
                    intent.putExtra("descripcion", producto.getDescripcion());
                    intent.putExtra("precio", producto.getPrecio());
                    intent.putExtra("stock", producto.getStock());
                    startActivity(intent);
                }
            });

            recyclerViewProductos.setAdapter(adapter);
        }
    }

    private List<Producto> obtenerProductosDemo() {
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto(1, "Notebook Lenovo", "Notebook 15' 8GB RAM SSD 256GB", 350000.0, 5, "Juan Pérez"));
        lista.add(new Producto(2, "Smartphone Samsung", "Galaxy A34 128GB 6GB RAM", 280000.0, 8, "María Gómez"));
        lista.add(new Producto(3, "Auriculares Bluetooth", "Auriculares Inalámbricos Noise Cancelling", 45000.0, 15, "Tech Store"));
        lista.add(new Producto(4, "Monitor Gamer 24'", "Monitor Full HD 144Hz 1ms", 195000.0, 3, "Nexus Digital"));
        return lista;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            return true;
        } else if (id == R.id.menu_contact) {
            startActivity(new Intent(HomeActivity.this, ContactoActivity.class));
            return true;
        } else if (id == R.id.menu_logout) {
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            preferences.edit().clear().apply();

            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
