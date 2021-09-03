package com.example.alchilaxo.database

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cbUser")
data class UserRoomModel(
    @PrimaryKey val correoElectronico: String,
    @ColumnInfo val nombres: String,
    @ColumnInfo val deviceId: String,
    @ColumnInfo val tipo: Int,
    @ColumnInfo val numeroTelefono: String,
    @ColumnInfo val genero: String,
    @ColumnInfo val idOrigenCuenta: Int,
    @ColumnInfo val clave: String
)