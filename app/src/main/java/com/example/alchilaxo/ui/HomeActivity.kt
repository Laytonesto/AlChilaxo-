package com.example.alchilaxo.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.DefaultFlow
import com.example.alchilaxo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), DefaultFlow {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_home)


        val navView: BottomNavigationView = binding.navView
        initFlow()


        // Passing each menu ID as a set of Ids because each

        navView.setupWithNavController(navController)
    }

    override fun initFlow(){



    }

    fun openCategoryRestaurant(jsondata: String){


            val bundle = bundleOf("restaurant" to jsondata)
            navController.navigate(R.id.navigation_restaurant_category, bundle)



    }

    fun openAddDirecciones(){

        navController.navigate(R.id.navigation_dashboard)
    }
}