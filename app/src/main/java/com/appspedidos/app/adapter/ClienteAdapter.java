package com.appspedidos.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appspedidos.app.R;
import com.appspedidos.app.data.entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    public interface OnClienteClickListener {
        void onClienteClick(Cliente cliente);
    }

    private List<Cliente> clientes = new ArrayList<>();
    private final OnClienteClickListener listener;

    public ClienteAdapter(OnClienteClickListener listener) {
        this.listener = listener;
    }

    public void setClientes(List<Cliente> nuevaLista) {
        this.clientes = nuevaLista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = clientes.get(position);
        holder.tvNombre.setText(cliente.nombre);
        holder.tvTelefono.setText(cliente.telefono != null ? cliente.telefono : "");

        String direccion = "";
        if (cliente.calleNumero != null) direccion += cliente.calleNumero;
        if (cliente.ciudad != null) direccion += (direccion.isEmpty() ? "" : ", ") + cliente.ciudad;
        holder.tvDireccion.setText(direccion);

        holder.itemView.setOnClickListener(v -> listener.onClienteClick(cliente));
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvTelefono, tvDireccion;

        ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreCliente);
            tvTelefono = itemView.findViewById(R.id.tvTelefonoCliente);
            tvDireccion = itemView.findViewById(R.id.tvDireccionCliente);
        }
    }
}
