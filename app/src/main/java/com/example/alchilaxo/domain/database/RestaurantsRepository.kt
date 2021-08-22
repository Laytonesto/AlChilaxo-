package com.example.alchilaxo.domain.database

import androidx.lifecycle.LiveData
import com.example.alchilaxo.domain.database.RestaurantsDao
import com.example.alchilaxo.domain.entities.RestaurantsRow

class RestaurantsRepository(private val restaurantsDao: RestaurantsDao) {



    suspend fun insertRestaurantsList(restaurantsList: List<RestaurantsRow>) {
        val restaurantsListResponse: MutableList<RestaurantsRoomModel> = mutableListOf()

        // Parsing 'Restaurants' to 'RestaurantsRoomModel'
        for (restaurants in restaurantsList) {
            val restaurantsObject = RestaurantsRoomModel(
                restaurants.id,
                restaurants.nombre ?: "",
                restaurants.imagen ?: ""
            )

            restaurantsListResponse.add(restaurantsObject)
        }

        restaurantsDao.insertRestaurantsList(restaurantsListResponse)
    }

    suspend fun insertRestaurants(restaurantsadd: RestaurantsRoomModel) {

        restaurantsDao.insertRestaurantsone(restaurantsadd)
    }

    suspend fun getAllRestaurants(): List<RestaurantsRow> {
        val restaurantsList = restaurantsDao.getRestaurantList()

        return if (restaurantsList.isEmpty()) {
            listOf()
        }
        else {
            val restaurantsArray: MutableList<RestaurantsRow> = mutableListOf()

            for (restaurants in restaurantsList) {
                val restaurantsObject = RestaurantsRow(
                    restaurants.uid,
                    restaurants.name,
                    restaurants.imageName
                )
                restaurantsArray.add(restaurantsObject)
            }

            restaurantsArray
        }
    }

    suspend fun getAllFilterRestaurants(clases: String): List<RestaurantsRow> {
        val restaurantsList = restaurantsDao.getRestaurantFilterList(clases)

        return if (restaurantsList.isEmpty()) {
            listOf()
        }
        else {
            val restaurantsArray: MutableList<RestaurantsRow> = mutableListOf()

            for (restaurants in restaurantsList) {
                val restaurantsObject = RestaurantsRow(
                    restaurants.uid,
                    restaurants.name,
                    restaurants.imageName
                )
                restaurantsArray.add(restaurantsObject)
            }

            restaurantsArray
        }
    }

    fun getAllFilterRestaurants2(clases: String): List<RestaurantsRow> {
        val restaurantsList = restaurantsDao.getRestaurantFilterList2(clases)

        return if (restaurantsList.isEmpty()) {
            listOf()
        }
        else {
            val restaurantsArray: MutableList<RestaurantsRow> = mutableListOf()

            for (restaurants in restaurantsList) {
                val restaurantsObject = RestaurantsRow(
                    restaurants.uid,
                    restaurants.name,
                    restaurants.imageName
                )
                restaurantsArray.add(restaurantsObject)
            }

            restaurantsArray
        }
    }

    fun getAllFilterRestaurantsid(id:String): LiveData<List<RestaurantsRoomModel>> {
        return restaurantsDao.getRestaurantPlusFilterList(id)
    }

    suspend fun deleteAll() {
        return restaurantsDao.deleteAll()
    }

}