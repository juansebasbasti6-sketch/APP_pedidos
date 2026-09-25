package com.appspedidos.app.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;
import java.util.List;
import com.appspedidos.app.data.entity.Producto;

@Dao
public interface ProductoDao {

    @Insert
    long insertar(Producto producto);

    @Update
    void actualizar(Producto producto);

    @Delete
    void eliminar(Producto producto);

    @Query("SELECT * FROM productos ORDER BY nombre ASC")
    List<Producto> obtenerTodos();

    @Query("SELECT * FROM productos WHERE id = :id")
    Producto obtenerPorId(int id);

    @Query("SELECT * FROM productos WHERE nombre LIKE '%' || :busqueda || '%'")
    List<Producto> buscarPorNombre(String busqueda);
}