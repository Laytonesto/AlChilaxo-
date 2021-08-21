package com.example.alchilaxo.core.di


import com.example.alchilaxo.di.ViewModelModule
import com.example.alchilaxo.ui.HomeActivity
import com.example.alchilaxo.ui.MainActivity
import com.example.alchilaxo.ui.admin.ui.main.AdminFragment
import com.example.alchilaxo.ui.admin.ui.main.AdminViewModel
import com.example.alchilaxo.ui.admin.ui.main.AdminViewModelFactory
import com.example.alchilaxo.ui.home.HomeFragment
import com.example.alchilaxo.viewmodel.*
import dagger.Component

@AppScopes
@Component(modules = [ViewModelModule::class])
interface AppComponent {

    /** Activities
     * */
    fun inject(MainActivity: MainActivity)
    fun inject(HomeActivity: HomeActivity)

    /** Main Home View
     * */

    fun inject(HomeFragment: HomeFragment)
    fun inject(HomeFragmentViewModel: HomeFragmentViewModel)
    fun inject(HomeFragmentViewModelFactory: HomeFragmentViewModelFactory)

    fun inject(AdminFragment: AdminFragment)
    fun inject(AdminViewModel: AdminViewModel)
    fun inject(AdminViewModelFactory: AdminViewModelFactory)

    /** Side Menu Views
     * */



    /** TabBar Views
     * */



}