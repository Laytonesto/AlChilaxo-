package com.example.alchilaxo.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

class CategoryFragmentViewModel(private val dispatcher: CoroutineDispatcher,
                                private val db: FirebaseFirestore
): ViewModel() {


}