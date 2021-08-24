package com.example.alchilaxo.domain.database

import com.example.alchilaxo.domain.entities.PromocionesInit

class InitPromosRepository(private val initpromosDao: InitPromosDao) {

    suspend fun insertInitPromosList(promosList: List<PromocionesInit>){
        val promosListResponse: MutableList<InitPromosRoomModel> = mutableListOf()

        for (promos in promosList){
            val promosObject = InitPromosRoomModel(
                promos.id,
                promos.nombre ?: "",
                promos.imagen ?: "",
                promos.descripcion ?: ""
            )
            promosListResponse.add(promosObject)
        }
        initpromosDao.insertInitPromosList(promosListResponse)
    }

    suspend fun getAllInitPromos(): List<PromocionesInit> {
        val promosList = initpromosDao.getInitPromosList()
        return if (promosList.isEmpty()){
            listOf()
        }
        else {
            val promosArray: MutableList<PromocionesInit> = mutableListOf()

            for (promos in promosList){
                val promoObject = PromocionesInit(
                    promos.uid,
                    promos.nombre,
                    promos.imagen,
                    promos.descripcion
                )
                promosArray.add(promoObject)
            }
            promosArray

        }
    }

}