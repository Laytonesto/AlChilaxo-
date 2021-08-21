package com.example.alchilaxo.domain.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cbRestaurants")
data class RestaurantsRoomModel(
    @PrimaryKey val uid: String,
    @ColumnInfo val name: String,
    @ColumnInfo val imageName: String,
)