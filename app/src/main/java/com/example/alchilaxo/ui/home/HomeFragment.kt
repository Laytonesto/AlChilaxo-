package com.example.alchilaxo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.log
import com.example.alchilaxo.databinding.FragmentHomeBinding
import com.example.alchilaxo.domain.RestaurantsClasesModel
import com.example.alchilaxo.domain.entities.RestaurantsModel
import com.example.alchilaxo.ui.HomeActivity
import com.example.alchilaxo.viewmodel.HomeFragmentViewModel
import com.example.alchilaxo.viewmodel.HomeFragmentViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var listRestaurants : RestaurantsModel
    private lateinit var listD : RestaurantsClasesModel



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, HomeFragmentViewModelFactory()).get(HomeFragmentViewModel::class.java)


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        loadInit()


        return root
    }



    private fun loadInit(){

        viewModel.generateRestaurants()


        viewModel.getRestaurantsDataOptions().observe(viewLifecycleOwner, Observer {
            it?.let {
                listD = it
                loadInitData()
            }
        })

    }

    private fun loadInitData(){

        val f = listD.clases?: listOf()

        binding.txtprueba.text = f[0].description    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}