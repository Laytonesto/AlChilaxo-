package com.example.alchilaxo.core.di


import com.example.alchilaxo.di.ViewModelModule
import com.example.alchilaxo.ui.HomeActivity
import com.example.alchilaxo.ui.LoginActivity
import com.example.alchilaxo.ui.MainActivity
import com.example.alchilaxo.ui.admin.ui.main.AdminFragment
import com.example.alchilaxo.ui.admin.ui.main.AdminViewModel
import com.example.alchilaxo.ui.admin.ui.main.AdminViewModelFactory
import com.example.alchilaxo.ui.home.HomeFragment
import com.example.alchilaxo.ui.sidemenu.CategoryFragment
import com.example.alchilaxo.ui.sidemenu.PerfilFragment
import com.example.alchilaxo.viewmodel.*
import dagger.Component

@AppScopes
@Component(modules = [ViewModelModule::class])
interface AppComponent {

    /** Activities
     * */
    fun inject(MainActivity: MainActivity)
    fun inject(HomeActivity: HomeActivity)
    fun inject(LoginActivity: LoginActivity)
    fun inject(HomeActivityViewModel: HomeActivityViewModel)
    fun inject(HomeActivityViewModelFactory: HomeActivityViewModelFactory)
    fun inject(LoginActivityViewModel: LoginActivityViewModel)
    fun inject(LoginActivityViewModelFactory: LoginActivityViewModelFactory)

    /** Main Home View
     * */

    fun inject(HomeFragment: HomeFragment)
    fun inject(HomeFragmentViewModel: HomeFragmentViewModel)
    fun inject(HomeFragmentViewModelFactory: HomeFragmentViewModelFactory)

    fun inject(AdminFragment: AdminFragment)
    fun inject(AdminViewModel: AdminViewModel)
    fun inject(AdminViewModelFactory: AdminViewModelFactory)

    fun inject(CategoryFragment: CategoryFragment)
    fun inject(CategoryFragmentViewModel: CategoryFragmentViewModel)
    fun inject(CategoryFragmentViewModelFactory: CategoryFragmentViewModelFactory)

    fun inject(PerfilFragment: PerfilFragment)
    fun inject(PerfilFragmentViewModel: PerfilFragmentViewModel)
    fun inject(PerfilFragmentViewModelFactory: PerfilFragmentViewModelFactory)



    /** Side Menu Views
     * */



    /** TabBar Views
     * */



}