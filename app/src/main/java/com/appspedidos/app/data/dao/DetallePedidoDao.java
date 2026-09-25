package com.appspedidos.app.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;
import java.util.List;
import com.appspedidos.app.data.entity.DetallePedido;

@Dao
public interface DetallePedidoDao {

    @Insert
    long insertar(DetallePedido detalle);

    @Update
    void actualizar(DetallePedido detalle);

    @Delete
    void eliminar(DetallePedido detalle);

    @Query("SELECT * FROM detalle_pedido WHERE pedidoId = :pedidoId")
    List<DetallePedido> obtenerPorPedido(int pedidoId);
}
