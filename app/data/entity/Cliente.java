package com.appspedidos.app.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clientes")
public class Cliente {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String nombre;

    public String telefono;
    public String calleNumero;
    public String barrio;
    public String ciudad;
    public String departamento;
    public String referencia;
    public String notas;

    public Cliente(@NonNull String nombre, String telefono, String calleNumero,
                    String barrio, String ciudad, String departamento,
                    String referencia, String notas) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.calleNumero = calleNumero;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.referencia = referencia;
        this.notas = notas;
    }
}