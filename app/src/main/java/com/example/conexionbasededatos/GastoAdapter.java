package com.example.conexionbasededatos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.GastoViewHolder> {
    private List<Gasto> gastos;

    public GastoAdapter(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    @NonNull
    @Override
    public GastoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gasto, parent, false);
        return new GastoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoViewHolder holder, int position) {
        Gasto gasto = gastos.get(position);
        holder.nombreTextView.setText("Nombre: " + gasto.getNombre());
        holder.tipoTextView.setText("Tipo: " + gasto.getTipo());
        holder.cantidadTextView.setText("Cantidad: $" + gasto.getCantidad());
        holder.fechaTextView.setText("Fecha: " + gasto.getFecha());
    }

    @Override
    public int getItemCount() {
        return gastos.size();
    }

    public void actualizarGastos(List<Gasto> nuevosGastos) {
        gastos = nuevosGastos;
        notifyDataSetChanged();
    }

    static class GastoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView tipoTextView;
        TextView cantidadTextView;
        TextView fechaTextView;

        public GastoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            tipoTextView = itemView.findViewById(R.id.textViewTipo);
            cantidadTextView = itemView.findViewById(R.id.textViewCantidad);
            fechaTextView = itemView.findViewById(R.id.textViewFecha);
        }
    }

}
