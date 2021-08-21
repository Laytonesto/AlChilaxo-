package com.example.alchilaxo.domain.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.alchilaxo.database.UserRoomModel


@Dao
interface UserDao {
    @Query("SELECT * From cbUser")
    fun getAll(): LiveData<List<UserRoomModel>>

    @Query("SELECT * From cbUser")
    suspend fun getAlll(): List<UserRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserRoomModel)

    @Delete
    suspend fun delete(user: UserRoomModel)

    @Update
    suspend fun update(user: UserRoomModel)

}