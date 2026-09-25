package com.appspedidos.app.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
    tableName = "detalle_pedido",
    foreignKeys = {
        @ForeignKey(
            entity = Pedido.class,
            parentColumns = "id",
            childColumns = "pedidoId"
        ),
        @ForeignKey(
            entity = Producto.class,
            parentColumns = "id",
            childColumns = "productoId"
        )
    },
    indices = {@Index("pedidoId"), @Index("productoId")}
)
public class DetallePedido {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int pedidoId;
    public int productoId;
    public int cantidad;
    public double precioUnitario;
    public double totalPedido;

    public DetallePedido(int pedidoId, int productoId, int cantidad,
                          double precioUnitario, double totalPedido) {
        this.pedidoId = pedidoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalPedido = totalPedido;
    }
}