package com.appspedidos.app.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "productos")
public class Producto {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String nombre;

    public double precio;
    public String laboratorio;

    public Producto(@NonNull String nombre, double precio, String laboratorio) {
        this.nombre = nombre;
        this.precio = precio;
        this.laboratorio = laboratorio;
    }
}