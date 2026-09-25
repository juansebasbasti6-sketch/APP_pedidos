package com.appspedidos.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.appspedidos.app.data.AppDatabase;
import com.appspedidos.app.data.AppExecutors;
import com.appspedidos.app.data.entity.Cliente;

public class NuevoClienteActivity extends AppCompatActivity {

    private AppDatabase db;
    private int clienteId = -1;

    private EditText etNombre, etTelefono, etCalleNumero, etBarrio,
            etCiudad, etDepartamento, etReferencia, etNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cliente);

        db = AppDatabase.getInstance(this);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etCalleNumero = findViewById(R.id.etCalleNumero);
        etBarrio = findViewById(R.id.etBarrio);
        etCiudad = findViewById(R.id.etCiudad);
        etDepartamento = findViewById(R.id.etDepartamento);
        etReferencia = findViewById(R.id.etReferencia);
        etNotas = findViewById(R.id.etNotas);
        Button btnGuardar = findViewById(R.id.btnGuardarCliente);

        clienteId = getIntent().getIntExtra("cliente_id", -1);
        if (clienteId != -1) {
            cargarCliente(clienteId);
        }

        btnGuardar.setOnClickListener(v -> guardarCliente());
    }

    private void cargarCliente(int id) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            Cliente cliente = db.clienteDao().obtenerPorId(id);
            runOnUiThread(() -> {
                if (cliente != null) {
                    etNombre.setText(cliente.nombre);
                    etTelefono.setText(cliente.telefono);
                    etCalleNumero.setText(cliente.calleNumero);
                    etBarrio.setText(cliente.barrio);
                    etCiudad.setText(cliente.ciudad);
                    etDepartamento.setText(cliente.departamento);
                    etReferencia.setText(cliente.referencia);
                    etNotas.setText(cliente.notas);
                }
            });
        });
    }

    private void guardarCliente() {
        String nombre = etNombre.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }

        String telefono = etTelefono.getText().toString().trim();
        String calleNumero = etCalleNumero.getText().toString().trim();
        String barrio = etBarrio.getText().toString().trim();
        String ciudad = etCiudad.getText().toString().trim();
        String departamento = etDepartamento.getText().toString().trim();
        String referencia = etReferencia.getText().toString().trim();
        String notas = etNotas.getText().toString().trim();

        AppExecutors.getInstance().diskIO().execute(() -> {
            if (clienteId != -1) {
                Cliente cliente = db.clienteDao().obtenerPorId(clienteId);
                cliente.nombre = nombre;
                cliente.telefono = telefono;
                cliente.calleNumero = calleNumero;
                cliente.barrio = barrio;
                cliente.ciudad = ciudad;
                cliente.departamento = departamento;
                cliente.referencia = referencia;
                cliente.notas = notas;
                db.clienteDao().actualizar(cliente);
            } else {
                Cliente nuevoCliente = new Cliente(nombre, telefono, calleNumero,
                        barrio, ciudad, departamento, referencia, notas);
                db.clienteDao().insertar(nuevoCliente);
            }

            runOnUiThread(() -> {
                Toast.makeText(this, "Cliente guardado", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}
