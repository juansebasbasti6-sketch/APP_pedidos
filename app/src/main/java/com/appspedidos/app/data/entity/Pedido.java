package com.appspedidos.app.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
    tableName = "pedidos",
    foreignKeys = @ForeignKey(
        entity = Cliente.class,
        parentColumns = "id",
        childColumns = "clienteId"
    ),
    indices = {@Index("clienteId")}
)
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int clienteId;
    public long fecha;
    public double total;

    public Pedido(int clienteId, long fecha, double total) {
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
    }
}