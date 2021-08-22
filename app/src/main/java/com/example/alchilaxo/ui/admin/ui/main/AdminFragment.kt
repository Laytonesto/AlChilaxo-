package com.example.alchilaxo.ui.admin.ui.main

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.DefaultFlow
import com.example.alchilaxo.core.util.DynamicAdapter
import com.example.alchilaxo.core.util.configureRecycler
import com.example.alchilaxo.databinding.FragmentAdminBinding
import com.example.alchilaxo.databinding.FragmentHomeBinding
import com.example.alchilaxo.databinding.ListAdminBinding
import com.example.alchilaxo.domain.RestaurantsClasesModel
import com.example.alchilaxo.domain.entities.RestaurantsModel
import com.example.alchilaxo.viewmodel.HomeFragmentViewModel
import com.example.alchilaxo.viewmodel.HomeFragmentViewModelFactory
import com.google.gson.Gson

class AdminFragment : Fragment(), DefaultFlow {

    private var _binding: FragmentAdminBinding? = null

    private val binding get() = _binding!!

    private lateinit var listCadena : RestaurantsModel




    companion object {
        fun newInstance() = AdminFragment()
    }

    private lateinit var viewModel: AdminViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this, AdminViewModelFactory()).get(
            AdminViewModel::class.java)

        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initFlow()


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun initFlow() {


        loadadmin()

    }

    private fun loadadmin(){

        //set datos
        binding.btnsetCadena.setOnClickListener {
            viewModel.generatecadena(binding.edtCadena.text.toString())
            viewModel.getcadena()

        }

        viewModel.getcadena()



        //get datos
        viewModel.getCadenaDataOptions().observe(viewLifecycleOwner, Observer {
            it?.let {
                listCadena = it
                loadCadenaData()
            }
        })

        viewModel.fetchLoadCadenas().observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.CadenasLoading.visibility = View.VISIBLE
                    binding.recyclerCadena.visibility = View.GONE
                } else {
                    binding.CadenasLoading.visibility = View.GONE
                    binding.recyclerCadena.visibility = View.VISIBLE

                }
            }
        })




    }

    private fun loadCadenaData(){

        //prueba con el txt
        val f = listCadena.restaurants?: listOf()

        binding.textView2.text = f[0].nombre


        //adaptador
        var adapter = DynamicAdapter(R.layout.list_admin,
            listCadena.restaurants ?: listOf(),
            fun(_, v, i, _) {
                v.findViewById<TextView>(R.id.txtCadena).text = i.nombre

                v.rootView.setOnClickListener {
                    Toast.makeText(v.context, i.nombre, Toast.LENGTH_SHORT).show()
                }

            }
        )

        binding.recyclerCadena.configureRecycler(adapter, true, true,1)
        binding.recyclerCadena.isNestedScrollingEnabled = false
        binding.recyclerCadena.setHasFixedSize(false)

    }

}