package com.example.alchilaxo.viewmodel

import androidx.lifecycle.*
import com.example.alchilaxo.core.Core
import com.example.alchilaxo.core.util.log
import com.example.alchilaxo.database.UserRoomModel
import com.example.alchilaxo.domain.Deal
import com.example.alchilaxo.domain.RestaurantsClasesModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.example.alchilaxo.domain.database.AppDatabase
import com.example.alchilaxo.domain.database.RestaurantsRepository
import com.example.alchilaxo.domain.database.UserRepository
import com.example.alchilaxo.domain.entities.RestaurantsModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val dispatcher: CoroutineDispatcher,
                            private val db: FirebaseFirestore
): ViewModel() {


    private var loadingPromos: MutableLiveData<Boolean> = MutableLiveData()
    private var loadingRestaurants: MutableLiveData<Boolean> = MutableLiveData()

    private val restaurantsDataSource = MutableLiveData<RestaurantsModel>()
    private val dealsDataSource = MutableLiveData<RestaurantsClasesModel>()

    //Data Repositories
    private val RestaurantsRepository: RestaurantsRepository
    private val userRepository: UserRepository


    init {
        val databaseReference = AppDatabase.getAppDataBase(
            Core.AppContext,
            viewModelScope,
            Core.AppContext.resources
        )

        userRepository = UserRepository(databaseReference.userDao())


        RestaurantsRepository = RestaurantsRepository(databaseReference.RestaurantsDao())
    }



    fun getRestaurantsDataOptions(): LiveData<RestaurantsClasesModel>{
        //return restaurantsDataSource
        return dealsDataSource
    }



    fun generateRestaurants() {

        loadingRestaurants.postValue(true)
        loadingPromos.postValue(true)

        viewModelScope.launch(dispatcher) {

            try {
                var del : List<Deal>? = listOf()
                var dela : String?


                db.collection("deals")
                    .get()
                    .addOnSuccessListener {
                        try {
                            dela = it.documents[0].get("title") as String?
                            log("Funciono: "+dela)
                            del = it.toObjects<Deal>()

                            log("Funciono: "+del)
                            dealsDataSource.postValue(RestaurantsClasesModel(del))
                            loadingRestaurants.postValue(false)
                            loadingPromos.postValue(false)




                        } catch (e: Exception) {
                            log("Error 1: "+e)
                        }
                    }.addOnFailureListener {
                        log("Error 2")

                    }


                // Getting restaurants from database
                val restaurantsList = RestaurantsRepository.getAllRestaurants()



            }
            catch (e: Exception) {
                log("Error: ${e.localizedMessage}")
                loadingRestaurants.postValue(false)
                loadingPromos.postValue(false)
            }
        }
    }

    fun getAccount(): LiveData<List<UserRoomModel>>{
        return userRepository.getAllUsers()
    }


}
