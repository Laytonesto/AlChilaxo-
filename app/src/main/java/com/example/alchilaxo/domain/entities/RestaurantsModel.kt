package com.example.alchilaxo.domain.entities

import com.google.firebase.firestore.DocumentId
import com.google.gson.annotations.SerializedName


data class RestaurantsModel(
    @SerializedName("cadenas")
    var restaurants: List<RestaurantsRow>? = listOf(),
    @SerializedName("promociones")
    var initpromos: List<PromocionesInit>? = listOf(),
    @SerializedName("cadenasFavoritas")
    var favorites: List<Favorite>? = listOf(),
    @SerializedName("clases")
    var clases: List<Clases>? = listOf(),
    @SerializedName("tipoTarjetaCredito")
    var tipoTarjetaCredito: List<TipoTarjeta>? = listOf()
)

data class RestaurantsRow(
    @DocumentId
    var id: String,
    var nombre: String = "",
    val imagen: String? = ""

){
    @Suppress("unused")
    constructor() : this("", "", "")
}

data class PromocionesInit(
    @DocumentId
    var id: String,
    var nombre: String = "",
    var imagen: String = "",
    var descripcion: String = ""
){
    @Suppress("unused")
    constructor() : this("", "", "","")
}

data class Favorite(
    var idCadena: Int,
    var nombre: String = "",
    val imagen: String? = ""
)

data class Clases(
    var idClase: Int,
    var nombre: String,
    var imagen: String? = ""
)

data class TipoTarjeta(
    var idTipoTarjetaCredito: Int,
    var nombre: String
)