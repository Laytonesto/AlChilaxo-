package com.example.alchilaxo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alchilaxo.core.util.log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginActivityViewModel(private val dispatcher: CoroutineDispatcher): ViewModel() {

    private val loadingLogin: MutableLiveData<Boolean> = MutableLiveData()


    fun generateLogin(user: String, password: String) {
        loadingLogin.postValue(true)
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

}