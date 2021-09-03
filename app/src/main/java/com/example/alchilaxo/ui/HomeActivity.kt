package com.example.alchilaxo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.DefaultFlow
import com.example.alchilaxo.core.util.log
import com.example.alchilaxo.database.UserRoomModel
import com.example.alchilaxo.databinding.ActivityHomeBinding
import com.example.alchilaxo.databinding.FragmentPerfilBinding
import com.example.alchilaxo.ui.sidemenu.PerfilFragment
import com.example.alchilaxo.viewmodel.HomeActivityViewModel
import com.example.alchilaxo.viewmodel.HomeActivityViewModelFactory
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity(), DefaultFlow {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: HomeActivityViewModel


    private var idUsuarioApp:String = ""

    private lateinit var listuser: List<UserRoomModel>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, HomeActivityViewModelFactory()).get(HomeActivityViewModel::class.java)


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

    fun actualizarUsers(): UserRoomModel {
        var iduser: UserRoomModel
        val id = runBlocking { viewModel.getAccountl() }

        if (id.isEmpty()){
            iduser = UserRoomModel("","","",0,"","",0,"")
            return iduser
        }
        else
        {
            iduser = id[0]


            idUsuarioApp = iduser.correoElectronico


            return iduser


        }


    }

    fun actualizarUser(): String {
        var iduser = ""
        viewModel.getAccount().observe(this, Observer {
            it?.let {
                listuser = it

                if (listuser.equals("")) {

                    idUsuarioApp = ""
                    iduser = ""
                }
                else {

                    iduser = listuser[0].correoElectronico
                    idUsuarioApp = listuser[0].correoElectronico
                }
            }
        })


        return iduser
    }

    fun openCategoryRestaurant(jsondata: String){

        gonetab()

        val bundle = bundleOf("restaurant" to jsondata)
        navController.navigate(R.id.navigation_restaurant_category, bundle)



    }

    fun openLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun gonetab(){
        binding.navView.visibility = View.GONE
    }

    fun visibletab(){
        binding.navView.visibility = View.VISIBLE
    }

    override fun onRestart() {
        super.onRestart()
        if(navController.currentDestination?.label.toString().equals("Perfil")){
            navController.navigateUp()

        }
    }


    fun openAddDirecciones(){

        navController.navigate(R.id.navigation_dashboard)
    }
}