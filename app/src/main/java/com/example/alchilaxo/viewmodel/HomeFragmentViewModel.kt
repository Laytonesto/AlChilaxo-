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
import com.example.alchilaxo.domain.database.InitPromosRepository
import com.example.alchilaxo.domain.database.RestaurantsRepository
import com.example.alchilaxo.domain.database.UserRepository
import com.example.alchilaxo.domain.entities.PromocionesInit
import com.example.alchilaxo.domain.entities.RestaurantsModel
import com.example.alchilaxo.domain.entities.RestaurantsRow
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.async
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
    private val InitPromosRepository: InitPromosRepository

    private val userRepository: UserRepository


    init {
        val databaseReference = AppDatabase.getAppDataBase(
            Core.AppContext,
            viewModelScope,
            Core.AppContext.resources
        )

        userRepository = UserRepository(databaseReference.userDao())
        InitPromosRepository = InitPromosRepository(databaseReference.initpromosDao())


        RestaurantsRepository = RestaurantsRepository(databaseReference.RestaurantsDao())
    }



    fun getRestaurantsDataOptions(): LiveData<RestaurantsModel>{
        //return restaurantsDataSource

        return restaurantsDataSource
    }



    fun generateRestaurants() {

        loadingRestaurants.postValue(true)
        loadingPromos.postValue(true)

        viewModelScope.launch(dispatcher) {

            try {
                // Getting restaurants from database
                val restaurantsList = RestaurantsRepository.getAllRestaurants()
                val promosList = InitPromosRepository.getAllInitPromos()


                if (restaurantsList.isEmpty()) {
                    var del: List<RestaurantsRow>? = listOf()

                    db.collection("cadenas").get().addOnSuccessListener {

                        val cadenas = it.toObjects<RestaurantsRow>()

                        log(cadenas.toString())

                        db.collection("promociones").get().addOnSuccessListener{

                            val InitPromos = it.toObjects<PromocionesInit>()

                            log(InitPromos.toString())

                            val restaurantsValues = RestaurantsModel(cadenas,InitPromos)

                            SetInitRoom(restaurantsValues)
                            restaurantsDataSource.postValue(restaurantsValues)
                            loadingRestaurants.postValue(false)
                            loadingPromos.postValue(false)


                        }

                    }


                    /*
                        RestaurantsRepository.insertRestaurantsList(docRef.await().result.toObjects<RestaurantsRow>())

                        val restaurantsValues = RestaurantsModel(docRef.await().result.toObjects<RestaurantsRow>())

                        restaurantsDataSource.postValue(restaurantsValues)
                        loadingRestaurants.postValue(false)
                        loadingPromos.postValue(false)

                     */




                }
                else {

                    val restaurantsValues = RestaurantsModel(restaurantsList,promosList)


                    restaurantsDataSource.postValue(restaurantsValues)
                    loadingRestaurants.postValue(false)
                    loadingPromos.postValue(false)
                }

            }
            catch (e: Exception) {
                log("Error: ${e.localizedMessage}")
                loadingRestaurants.postValue(false)
                loadingPromos.postValue(false)
            }
        }
    }


    fun SetInitRoom(LRestaurantsModel: RestaurantsModel) {

        viewModelScope.launch(dispatcher) {

            LRestaurantsModel.restaurants?.let { RestaurantsRepository.insertRestaurantsList(it) }
            LRestaurantsModel.initpromos?.let { InitPromosRepository.insertInitPromosList(it) }

        }
    }

    fun SetInitRoom2(LRestaurantsModel: List<RestaurantsRow>) {

        viewModelScope.launch(dispatcher) {

            RestaurantsRepository.insertRestaurantsList(LRestaurantsModel)

        }
    }




    fun fetchLoadRestaurants(): LiveData<Boolean> = loadingRestaurants




}
