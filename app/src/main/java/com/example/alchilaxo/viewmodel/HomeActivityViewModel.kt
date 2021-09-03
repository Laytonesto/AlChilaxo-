package com.example.alchilaxo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alchilaxo.core.Core
import com.example.alchilaxo.database.UserRoomModel
import com.example.alchilaxo.domain.database.AppDatabase
import com.example.alchilaxo.domain.database.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

class HomeActivityViewModel(private val dispatcher: CoroutineDispatcher,
                            private val db: FirebaseFirestore
): ViewModel() {


    private val userRepository: UserRepository

    init {
        val databaseReference = AppDatabase.getAppDataBase(
            Core.AppContext,
            viewModelScope,
            Core.AppContext.resources)

        userRepository = UserRepository(databaseReference.userDao())



    }


    fun getAccount(): LiveData<List<UserRoomModel>> {
        return userRepository.getAllUsers()
    }

    suspend fun getAccountl(): List<UserRoomModel>{
        return userRepository.getAllUsersl()
    }
}