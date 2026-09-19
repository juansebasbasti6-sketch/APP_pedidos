package com.appspedidos.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNuevoPedido = findViewById(R.id.btnNuevoPedido);
        Button btnClientes = findViewById(R.id.btnClientes);
        Button btnProductos = findViewById(R.id.btnProductos);
        Button btnHistorial = findViewById(R.id.btnHistorial);
        Button btnConfiguracion = findViewById(R.id.btnConfiguracion);

        btnNuevoPedido.setOnClickListener(v ->
                Toast.makeText(this, "Nuevo pedido (en construcción)", Toast.LENGTH_SHORT).show());

        btnClientes.setOnClickListener(v ->
                Toast.makeText(this, "Clientes (en construcción)", Toast.LENGTH_SHORT).show());

        btnProductos.setOnClickListener(v ->
                Toast.makeText(this, "Productos (en construcción)", Toast.LENGTH_SHORT).show());

        btnHistorial.setOnClickListener(v ->
                Toast.makeText(this, "Historial (en construcción)", Toast.LENGTH_SHORT).show());

        btnConfiguracion.setOnClickListener(v ->
                Toast.makeText(this, "Configuración (en construcción)", Toast.LENGTH_SHORT).show());
    }
}
