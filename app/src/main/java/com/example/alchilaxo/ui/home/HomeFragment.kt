package com.example.alchilaxo.ui.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.DefaultFlow
import com.example.alchilaxo.core.util.DynamicAdapter
import com.example.alchilaxo.core.util.configureRecycler
import com.example.alchilaxo.core.util.log
import com.example.alchilaxo.databinding.FragmentHomeBinding
import com.example.alchilaxo.domain.RestaurantsClasesModel
import com.example.alchilaxo.domain.entities.RestaurantsModel
import com.example.alchilaxo.ui.HomeActivity
import com.example.alchilaxo.viewmodel.HomeFragmentViewModel
import com.example.alchilaxo.viewmodel.HomeFragmentViewModelFactory
import com.google.gson.Gson

class HomeFragment : Fragment(), DefaultFlow {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var listRestaurants : RestaurantsModel



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


        initFlow()


        return root
    }

    override fun initFlow(){
        loadInit()
    }



    private fun loadInit(){

        viewModel.generateRestaurants()


        viewModel.getRestaurantsDataOptions().observe(viewLifecycleOwner, Observer {
            it?.let {
                listRestaurants = it
                loadInitData()
            }
        })

        viewModel.fetchLoadRestaurants().observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.restaurantsLoading.visibility = View.VISIBLE
                    binding.recyclerResta.visibility = View.GONE
                } else {
                    binding.restaurantsLoading.visibility = View.GONE
                    binding.recyclerResta.visibility = View.VISIBLE

                }
            }
        })

    }

    private fun loadInitData(){




        var adapter = DynamicAdapter(R.layout.list_home_restaurantes,
            listRestaurants.restaurants ?: listOf(),
            fun(_, v, i, _) {

                val btnRestaurant = v.findViewById<ImageButton>(R.id.btnRestaurant)
                val imageBytes = Base64.decode(i.imagen, Base64.DEFAULT);
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                btnRestaurant.setImageBitmap(decodedImage)

                btnRestaurant.setOnClickListener {


                    if (activity is HomeActivity) {
                        val restaurantJsonString = Gson().toJson(i)
                        (activity as HomeActivity).openCategoryRestaurant(restaurantJsonString)
                    }


                }

            }
        )

        val adapterpromos = DynamicAdapter(R.layout.list_home_promos,
            listRestaurants.initpromos ?: listOf(),

            fun(_, v, i, _) {
                val imageBytes = Base64.decode(i.imagen, Base64.DEFAULT);
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                v.findViewById<ImageButton>(R.id.btnPromos).setImageBitmap(decodedImage)

            }
        )


        binding.recyclerResta.configureRecycler(adapter, true, true)
        binding.recyclerResta.isNestedScrollingEnabled = false
        binding.recyclerResta.setHasFixedSize(false)

        binding.recyclerPromo.configureRecycler(adapterpromos, false)
        binding.recyclerPromo.isNestedScrollingEnabled = false
        binding.recyclerPromo.setHasFixedSize(false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}