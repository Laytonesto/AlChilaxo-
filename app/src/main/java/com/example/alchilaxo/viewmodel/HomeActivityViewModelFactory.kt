package com.example.alchilaxo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alchilaxo.core.Core
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers

class HomeActivityViewModelFactory: ViewModelProvider.Factory {


    private val db: FirebaseFirestore = Firebase.firestore
    private val storage = FirebaseStorage.getInstance().reference

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        Core.DI.inject(this)



        if (modelClass.isAssignableFrom(HomeActivityViewModel::class.java)) {
            return HomeActivityViewModel(Dispatchers.Main,db) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")


    }
}