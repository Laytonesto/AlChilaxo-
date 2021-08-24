package com.example.alchilaxo.domain.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cbInitPromos")
data class InitPromosRoomModel (
    @PrimaryKey val uid: String,
    @ColumnInfo val nombre: String,
    @ColumnInfo val imagen: String,
    @ColumnInfo val descripcion: String,
)
            
