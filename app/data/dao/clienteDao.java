package com.appspedidos.app.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;
import java.util.List;
import com.appspedidos.app.data.entity.Cliente;

@Dao
public interface ClienteDao {

    @Insert
    long insertar(Cliente cliente);

    @Update
    void actualizar(Cliente cliente);

    @Delete
    void eliminar(Cliente cliente);

    @Query("SELECT * FROM clientes ORDER BY nombre ASC")
    List<Cliente> obtenerTodos();

    @Query("SELECT * FROM clientes WHERE id = :id")
    Cliente obtenerPorId(int id);

    @Query("SELECT * FROM clientes WHERE nombre LIKE '%' || :busqueda || '%'")
    List<Cliente> buscarPorNombre(String busqueda);
}