package com.example.alchilaxo.ui.admin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alchilaxo.core.util.log
import com.example.alchilaxo.domain.Deal
import com.example.alchilaxo.domain.RestaurantsClasesModel
import com.example.alchilaxo.domain.entities.RestaurantsModel
import com.example.alchilaxo.domain.entities.RestaurantsRow
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AdminViewModel(private val dispatcher: CoroutineDispatcher,
                     private val db: FirebaseFirestore
): ViewModel()  {

    private var loadingCadenas: MutableLiveData<Boolean> = MutableLiveData()


    private val restaurantsDataSource = MutableLiveData<RestaurantsModel>()


    fun getCadenaDataOptions(): LiveData<RestaurantsModel> {
        //return restaurantsDataSource
        return restaurantsDataSource
    }


    fun generatecadena(nombre: String){
        viewModelScope.launch(dispatcher) {

            val cadena = RestaurantsRow("f", nombre,"link")

            db.collection("cadenas").document().set(cadena).addOnSuccessListener {
                log("Exito set")

            }.addOnFailureListener {
                log("Error 2")

            }



        }

    }

    fun getcadena(){
        loadingCadenas.postValue(true)

        viewModelScope.launch(dispatcher) {

            var cadenaF : List<RestaurantsRow>? = listOf()



            db.collection("cadenas")
                .get()
                .addOnSuccessListener {
                    try {
                        cadenaF = it.toObjects<RestaurantsRow>()


                        log("Funciono: "+it.documents)
                        log("Funciono: "+cadenaF)
                        restaurantsDataSource.postValue(RestaurantsModel(cadenaF))

                        loadingCadenas.postValue(false)



                    } catch (e: Exception) {
                        log("Error 1: "+e)
                    }
                }.addOnFailureListener {
                    log("Error 2")

                }


        }

    }

    fun fetchLoadCadenas(): LiveData<Boolean> = loadingCadenas



}