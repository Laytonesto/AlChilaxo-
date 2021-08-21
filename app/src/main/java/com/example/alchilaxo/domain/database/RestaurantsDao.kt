package com.example.alchilaxo.domain.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurantsList(restaList: List<RestaurantsRoomModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRestaurantsone(restaList: RestaurantsRoomModel)

    @Query("SELECT * FROM cbRestaurants")
    suspend fun getRestaurantList(): List<RestaurantsRoomModel>

    @Query("SELECT * FROM cbRestaurants WHERE uid = :clases")
    suspend fun getRestaurantFilterList(clases: String): List<RestaurantsRoomModel>

    @Query("SELECT * FROM cbRestaurants WHERE uid = :clases")
    fun getRestaurantFilterList2(clases: String): List<RestaurantsRoomModel>

    @Query("DELETE FROM cbRestaurants")
    suspend fun deleteAll()

    @Query("SELECT * FROM cbRestaurants WHERE uid = :idRestaurantes")
    fun getRestaurantPlusFilterList(idRestaurantes: String): LiveData<List<RestaurantsRoomModel>>



}