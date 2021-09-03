package com.example.alchilaxo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.DefaultFlow
import com.example.alchilaxo.databinding.ActivityHomeBinding
import com.example.alchilaxo.databinding.ActivityLoginBinding
import com.example.alchilaxo.viewmodel.LoginActivityViewModel
import com.example.alchilaxo.viewmodel.LoginActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginActivity : AppCompatActivity(), DefaultFlow {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var navController: NavController

    private lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_login)

        initFlow()



    }

    override fun initFlow() {
        viewModel = ViewModelProvider(this, LoginActivityViewModelFactory()).get(
            LoginActivityViewModel::class.java
        )



    }


    fun openRegister(){

        navController.navigate(R.id.navigation_register)


    }



}