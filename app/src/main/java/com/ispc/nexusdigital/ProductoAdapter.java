package com.ispc.nexusdigital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private Context context;
    private List<Producto> listaProductos;
    private OnProductoClickListener listener;

    // Interfaz para manejar los clicks en la lista
    public interface OnProductoClickListener {
        void onProductoClick(Producto producto);
    }

    // Constructor que recibe Context, lista y el listener de click
    public ProductoAdapter(Context context, List<Producto> listaProductos, OnProductoClickListener listener) {
        this.context = context;
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        if (holder.tvNombre != null) holder.tvNombre.setText(producto.getNombre());
        if (holder.tvDescripcion != null) holder.tvDescripcion.setText(producto.getDescripcion());
        if (holder.tvPrecio != null) holder.tvPrecio.setText(String.format("$ %.2f", producto.getPrecio()));
        if (holder.tvVendedor != null) holder.tvVendedor.setText("Vendedor: " + producto.getVendedor());

        // Evento de click sobre el elemento
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onProductoClick(producto);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos != null ? listaProductos.size() : 0;
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDescripcion, tvPrecio, tvVendedor;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvVendedor = itemView.findViewById(R.id.tvVendedor);
        }
    }
}
