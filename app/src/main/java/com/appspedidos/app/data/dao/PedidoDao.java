package com.appspedidos.app.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;
import java.util.List;
import com.appspedidos.app.data.entity.Pedido;

@Dao
public interface PedidoDao {

    @Insert
    long insertar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Delete
    void eliminar(Pedido pedido);

    @Query("SELECT * FROM pedidos ORDER BY fecha DESC")
    List<Pedido> obtenerTodos();

    @Query("SELECT * FROM pedidos WHERE id = :id")
    Pedido obtenerPorId(int id);

    @Query("SELECT * FROM pedidos WHERE clienteId = :clienteId ORDER BY fecha DESC")
    List<Pedido> obtenerPorCliente(int clienteId);
}
