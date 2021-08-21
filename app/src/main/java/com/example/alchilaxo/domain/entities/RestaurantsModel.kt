package com.example.alchilaxo.domain.entities

import com.google.gson.annotations.SerializedName


data class RestaurantsModel(
    @SerializedName("cadenas")
    var restaurants: List<RestaurantsRow>,
    @SerializedName("promociones")
    var initpromos: List<PromocionesInit>,
    @SerializedName("cadenasFavoritas")
    var favorites: List<Favorite>,
    @SerializedName("clases")
    var clases: List<Clases>,
    @SerializedName("tipoTarjetaCredito")
    var tipoTarjetaCredito: List<TipoTarjeta>
)

data class RestaurantsRow(
    var idCadena: Int,
    var nombre: String = "",
    val imagen: String? = ""
)

data class PromocionesInit(
    var idPromocion: Int,
    var nombre: String = "",
    var descripcion: String = ""
)

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