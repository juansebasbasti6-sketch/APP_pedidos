package com.appspedidos.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.appspedidos.app.adapter.ClienteAdapter;
import com.appspedidos.app.data.AppDatabase;
import com.appspedidos.app.data.AppExecutors;
import com.appspedidos.app.data.entity.Cliente;

import java.util.List;

public class ClientesActivity extends AppCompatActivity {

    private AppDatabase db;
    private ClienteAdapter adapter;
    private RecyclerView rvClientes;
    private TextView tvSinClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        db = AppDatabase.getInstance(this);

        rvClientes = findViewById(R.id.rvClientes);
        tvSinClientes = findViewById(R.id.tvSinClientes);
        EditText etBuscar = findViewById(R.id.etBuscarCliente);
        FloatingActionButton fabAgregar = findViewById(R.id.fabAgregarCliente);

        adapter = new ClienteAdapter(cliente -> {
            Intent intent = new Intent(ClientesActivity.this, NuevoClienteActivity.class);
            intent.putExtra("cliente_id", cliente.id);
            startActivity(intent);
        });

        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        rvClientes.setAdapter(adapter);

        fabAgregar.setOnClickListener(v ->
                startActivity(new Intent(ClientesActivity.this, NuevoClienteActivity.class))
        );

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscarClientes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        cargarClientes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarClientes();
    }

    private void cargarClientes() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Cliente> lista = db.clienteDao().obtenerTodos();
            runOnUiThread(() -> mostrarClientes(lista));
        });
    }

    private void buscarClientes(String texto) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Cliente> lista = texto.isEmpty()
                    ? db.clienteDao().obtenerTodos()
                    : db.clienteDao().buscarPorNombre(texto);
            runOnUiThread(() -> mostrarClientes(lista));
        });
    }

    private void mostrarClientes(List<Cliente> lista) {
        adapter.setClientes(lista);
        tvSinClientes.setVisibility(lista.isEmpty() ? android.view.View.VISIBLE : android.view.View.GONE);
    }
}
