package com.example.alchilaxo.domain.entities

import com.google.firebase.firestore.DocumentId

data class LoginModel(
    var usuario: UserDataObject
)

data class UserDataObject(
    @DocumentId
    var correoElectronico: String = "",
    var nombre: String = "",
    var deviceID: String = "",
    var tipo: Int,
    var numeroTelefono: String = "",
    var genero: String = "",
    var idOrigenCuenta: Int,
    var clave: String = ""
){
    @Suppress("unused")
    constructor() : this("", "", "", 0, "", "", 0, "")
}