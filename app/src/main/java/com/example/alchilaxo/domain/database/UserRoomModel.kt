package com.example.alchilaxo.database

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cbUser")
data class UserRoomModel(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo val nombres: String,
    @ColumnInfo val apellidos: String,
    @ColumnInfo val deviceId: String,
    @ColumnInfo val idOs: String,
    @ColumnInfo val numeroTelefono: String,
    @ColumnInfo val idPais: String,
    @ColumnInfo val genero: String,
    @ColumnInfo val idOrigenCuenta: String,
    @ColumnInfo val nit: String,
    @ColumnInfo val idUsuarioApp: String,
    @ColumnInfo val correoElectronico: String,
    @ColumnInfo val clave: String
)