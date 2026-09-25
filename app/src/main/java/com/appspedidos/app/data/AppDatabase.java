package com.appspedidos.app.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.appspedidos.app.data.entity.Cliente;
import com.appspedidos.app.data.entity.Producto;
import com.appspedidos.app.data.entity.Pedido;
import com.appspedidos.app.data.entity.DetallePedido;

import com.appspedidos.app.data.dao.ClienteDao;
import com.appspedidos.app.data.dao.ProductoDao;
import com.appspedidos.app.data.dao.PedidoDao;
import com.appspedidos.app.data.dao.DetallePedidoDao;

@Database(
    entities = {Cliente.class, Producto.class, Pedido.class, DetallePedido.class},
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ClienteDao clienteDao();
    public abstract ProductoDao productoDao();
    public abstract PedidoDao pedidoDao();
    public abstract DetallePedidoDao detallePedidoDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_pedidos_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
