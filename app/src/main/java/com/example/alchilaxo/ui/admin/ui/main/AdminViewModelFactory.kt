package com.example.alchilaxo.ui.admin.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alchilaxo.core.Core
import com.example.alchilaxo.viewmodel.HomeFragmentViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers

class AdminViewModelFactory: ViewModelProvider.Factory  {

    private val db: FirebaseFirestore = Firebase.firestore
    private val storage = FirebaseStorage.getInstance().reference
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Core.DI.inject(this)



        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            return AdminViewModel(Dispatchers.Main,db) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}