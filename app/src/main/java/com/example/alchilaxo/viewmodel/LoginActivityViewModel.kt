package com.example.alchilaxo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alchilaxo.core.Core
import com.example.alchilaxo.core.util.log
import com.example.alchilaxo.database.UserRoomModel
import com.example.alchilaxo.domain.database.AppDatabase
import com.example.alchilaxo.domain.database.UserRepository
import com.example.alchilaxo.domain.entities.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import java.lang.Exception

class LoginActivityViewModel(private val dispatcher: CoroutineDispatcher,
                             private val db: FirebaseFirestore
): ViewModel() {

    private val loadingLogin: MutableLiveData<Boolean> = MutableLiveData()
    private val emailError: MutableLiveData<Boolean> = MutableLiveData()
    private val claveError: MutableLiveData<Boolean> = MutableLiveData()

    private var loadingFin: MutableLiveData<Boolean> = MutableLiveData()

    private val loginDataSource = MutableLiveData<UserDataObject>()


    private val userRepository: UserRepository

    init {
        val databaseReference = AppDatabase.getAppDataBase(
            Core.AppContext,
            viewModelScope,
            Core.AppContext.resources)

        userRepository = UserRepository(databaseReference.userDao())
    }

    fun getLoginDataOptions(): LiveData<UserDataObject> {
        return loginDataSource
    }



    fun generateLogin(email: String, password: String) {
        loadingLogin.postValue(true)

        viewModelScope.launch(dispatcher) {
            try {

                db.collection("usuarios").document(email).get().addOnSuccessListener {
                    val clave = it.get("clave") as String?
                    loadingLogin.postValue(false)

                    if(!it.exists()){
                       emailError.postValue(true)

                    }
                    else{
                        if (clave.equals(password)){
                            claveError.postValue(false)
                            emailError.postValue(false)





                            val user = it.toObject<UserDataObject>()

                            loginDataSource.postValue(user!!)


                        }

                        else{
                            claveError.postValue(true)


                        }
                    }


                }.addOnFailureListener {
                }
            }
            catch (e : Exception) {
                log("Error: ${e.localizedMessage}")
                loadingLogin.postValue(false)

            }

        }
/*
        viewModelScope.launch(dispatcher) {
            try {
                val response = accountNetworkServices.fetchLogin(user, password)

                if (response.isSuccessful) {
                    log("Login Request Success")
                    loginDataSource.postValue(response.body())
                    loadingLogin.postValue(false)
                }
                else {
                    log("Login Request Failure")
                    loadingLogin.postValue(false)
                }
            }
            catch (e : Exception) {
                log("Error: ${e.localizedMessage}")
                loadingLogin.postValue(false)
            }
        }

 */

    }

    fun generateRegister(body: UserDataObject) {


        viewModelScope.launch(dispatcher) {
            try {
                db.collection("usuarios").document(body.correoElectronico).set(body).addOnSuccessListener {
                    loginDataSource.postValue(body)

                }.addOnFailureListener {
                    loadingFin.postValue(true)
                }
            }
            catch (e : Exception) {
                log("Error: ${e.localizedMessage}")


            }
        }
    }

    fun saveUser(user: UserRoomModel) = viewModelScope.launch {
        userRepository.saveUser(user)
    }

    fun fetchLoadFin(): LiveData<Boolean> = loadingFin


    fun fetchLoadLogin(): LiveData<Boolean> = loadingLogin
    fun fetchClave(): LiveData<Boolean> = claveError
    fun fetchEmail(): LiveData<Boolean> = emailError





}