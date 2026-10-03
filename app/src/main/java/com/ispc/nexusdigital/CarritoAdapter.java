package com.ispc.nexusdigital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {

    public interface OnCarritoChangedListener {
        void onCarritoChanged();
    }

    private final List<CarritoItem> listaCarrito;
    private final OnCarritoChangedListener listener;

    public CarritoAdapter(
            List<CarritoItem> listaCarrito,
            OnCarritoChangedListener listener
    ) {
        this.listaCarrito = listaCarrito;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrito, parent, false);

        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull CarritoViewHolder holder,
            int position
    ) {
        CarritoItem item = listaCarrito.get(position);
        Producto producto = item.getProducto();

        holder.tvNombreProducto.setText(producto.getNombre());

        holder.tvPrecioProducto.setText(
                "$ " + String.format("%.2f", producto.getPrecio())
        );

        holder.tvCantidad.setText(String.valueOf(item.getCantidad()));

        holder.btnMas.setOnClickListener(v -> {

            if (item.getCantidad() < producto.getStock()) {
                item.setCantidad(item.getCantidad() + 1);

                notifyItemChanged(position);
                listener.onCarritoChanged();
            }
        });

        holder.btnMenos.setOnClickListener(v -> {

            if (item.getCantidad() > 1) {
                item.setCantidad(item.getCantidad() - 1);

                notifyItemChanged(position);
                listener.onCarritoChanged();
            }
        });

        holder.btnEliminar.setOnClickListener(v -> {

            listaCarrito.remove(position);

            notifyItemRemoved(position);
            listener.onCarritoChanged();
        });
    }

    @Override
    public int getItemCount() {
        return listaCarrito.size();
    }

    static class CarritoViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreProducto;
        TextView tvPrecioProducto;
        TextView tvCantidad;

        Button btnMenos;
        Button btnMas;
        Button btnEliminar;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvPrecioProducto = itemView.findViewById(R.id.tvPrecioProducto);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);

            btnMenos = itemView.findViewById(R.id.btnMenos);
            btnMas = itemView.findViewById(R.id.btnMas);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
