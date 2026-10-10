package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.ispc.nexusdigital.api.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private ProductoAdapter adapter;
    private List<Producto> listaProductos;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SessionManager sessionManager;
    private EditText etBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        sessionManager = new SessionManager(this);

        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, android.R.color.white));

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_perfil) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_contacto) {
                Intent intent = new Intent(HomeActivity.this, ContactoActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_carrito) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_cerrar_sesion) {
                cerrarSesion();
            }

            drawerLayout.closeDrawers();
            return true;
        });

        rvProductos = findViewById(R.id.rvProductos);
        rvProductos.setLayoutManager(new LinearLayoutManager(this));

        listaProductos = obtenerDatosDeEjemplo();

        adapter = new ProductoAdapter(listaProductos, producto -> {
            Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
            intent.putExtra("id_producto", producto.getIdProducto());
            intent.putExtra("producto", producto);
            startActivity(intent);
        });

        rvProductos.setAdapter(adapter);

        etBuscar = findViewById(R.id.etBuscar);
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarProductos(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void cerrarSesion() {
        sessionManager.cerrarSesion();

        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void filtrarProductos(String texto) {
        String query = texto.trim().toLowerCase();
        List<Producto> filtrados = new ArrayList<>();

        for (Producto p : listaProductos) {
            String nombre = p.getNombre() != null ? p.getNombre().toLowerCase() : "";
            String descripcion = p.getDescripcion() != null ? p.getDescripcion().toLowerCase() : "";

            if (nombre.contains(query) || descripcion.contains(query)) {
                filtrados.add(p);
            }
        }

        adapter.actualizarLista(filtrados);
    }

    private List<Producto> obtenerDatosDeEjemplo() {
        List<Producto> lista = new ArrayList<>();

        lista.add(new Producto(
                1,
                "Notebook Lenovo",
                "Notebook 15\" 8GB RAM",
                350000.0,
                5,
                10,
                "Juan Perez"
        ));

        lista.add(new Producto(
                2,
                "Bicicleta Rodado 29",
                "Bicicleta montaña",
                180000.0,
                2,
                11,
                "Maria Lopez"
        ));

        lista.add(new Producto(
                3,
                "Silla Gamer",
                "Silla ergonómica reclinable",
                95000.0,
                8,
                12,
                "Carlos Diaz"
        ));

        return lista;
    }
}