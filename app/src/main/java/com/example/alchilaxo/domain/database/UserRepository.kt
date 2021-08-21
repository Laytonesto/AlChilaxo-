package com.example.alchilaxo.domain.database

import androidx.lifecycle.LiveData
import com.example.alchilaxo.database.UserRoomModel

class UserRepository(private val userDao: UserDao)  {

    fun getAllUsers(): LiveData<List<UserRoomModel>> {
        return userDao.getAll()
    }
    suspend fun getAllUsersl(): List<UserRoomModel> {
        return userDao.getAlll()
    }


    suspend fun saveUser(user: UserRoomModel) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: UserRoomModel) {
        userDao.update(user)
    }

    suspend fun deleteUser(user: UserRoomModel) {
        userDao.delete(user)
    }
}