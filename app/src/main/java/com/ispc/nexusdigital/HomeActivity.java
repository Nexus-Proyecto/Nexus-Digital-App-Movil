package com.ispc.nexusdigital;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private ProductoAdapter adapter;
    private List<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

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
    }

    private List<Producto> obtenerDatosDeEjemplo() {
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto(1, "Notebook Lenovo", "Notebook 15\" 8GB RAM", 350000.0, 5, 10, "Juan Perez"));
        lista.add(new Producto(2, "Bicicleta Rodado 29", "Bicicleta montaña", 180000.0, 2, 11, "Maria Lopez"));
        lista.add(new Producto(3, "Silla Gamer", "Silla ergonómica reclinable", 95000.0, 8, 12, "Carlos Diaz"));
        return lista;
    }
}