package com.example.alchilaxo.ui.sidemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.DefaultFlow
import com.example.alchilaxo.databinding.FragmentCategoryBinding
import com.example.alchilaxo.databinding.FragmentHomeBinding
import com.example.alchilaxo.domain.entities.RestaurantsRow
import com.example.alchilaxo.viewmodel.CategoryFragmentViewModel
import com.example.alchilaxo.viewmodel.CategoryFragmentViewModelFactory
import com.google.gson.Gson


class CategoryFragment : Fragment(), DefaultFlow {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CategoryFragmentViewModel

    private lateinit var restaurant: RestaurantsRow

    var data = ""
    var idUsuarioApp = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val restaurantString = arguments?.getString("restaurant")
        val idUsuario = arguments?.getString("idUsuarioApp")


        data = restaurantString.toString()
        idUsuarioApp = idUsuario.toString()

        if (restaurantString != null) {
            restaurant = Gson().fromJson(restaurantString, RestaurantsRow::class.java)

            initFlow()
        }


        return root
    }

    override fun initFlow() {

        viewModel = ViewModelProvider(this, CategoryFragmentViewModelFactory()).get(
            CategoryFragmentViewModel::class.java
        )

        binding.txtNameRestaurant.setText(restaurant.nombre)


    }

}