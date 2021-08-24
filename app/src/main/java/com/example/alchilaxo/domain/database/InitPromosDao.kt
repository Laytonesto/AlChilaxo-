package com.example.alchilaxo.domain.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InitPromosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInitPromosList(initpromosList: List<InitPromosRoomModel>)

    @Query("SELECT * FROM cbInitPromos")
    suspend fun getInitPromosList(): List<InitPromosRoomModel>
}