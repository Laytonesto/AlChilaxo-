package com.example.alchilaxo.ui.sidemenu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.DefaultFlow
import com.example.alchilaxo.database.UserRoomModel
import com.example.alchilaxo.databinding.FragmentNotificationsBinding
import com.example.alchilaxo.databinding.FragmentPerfilBinding
import com.example.alchilaxo.ui.HomeActivity
import com.example.alchilaxo.ui.LoginActivity
import com.example.alchilaxo.ui.notifications.NotificationsViewModel
import com.example.alchilaxo.viewmodel.LoginActivityViewModel
import com.example.alchilaxo.viewmodel.LoginActivityViewModelFactory
import com.example.alchilaxo.viewmodel.PerfilFragmentViewModel
import com.example.alchilaxo.viewmodel.PerfilFragmentViewModelFactory
import com.google.firebase.firestore.FirebaseFirestore


class PerfilFragment : Fragment(), DefaultFlow {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentPerfilBinding? = null
    private lateinit var viewModel: PerfilFragmentViewModel

    var user: UserRoomModel? = null
    private lateinit var listuser: List<UserRoomModel>


    private val db = FirebaseFirestore.getInstance()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*
        binding.btnp.setOnClickListener {
            db.collection("user").document("prueba").set(
                hashMapOf("Name" to "dato_prueba", "adress" to "otra cosa", "provider" to "fsddsf")

            )
        }

         */






        //db.collection("datos_iniciales").document()

        if (activity is HomeActivity) {

            user = (activity as HomeActivity).actualizarUsers()
            if (user?.correoElectronico.equals("")) {


                (activity as HomeActivity).openLogin()



            }
            else{
                binding.lload.visibility = View.GONE
                initFlow()

            }

        }



        return root
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onStop() {
        super.onStop()
    }

    override fun initFlow() {
        viewModel = ViewModelProvider(this, PerfilFragmentViewModelFactory()).get(
            PerfilFragmentViewModel::class.java
        )

        binding.txtNameA.text = user!!.nombres
        binding.txtNameP.text = user!!.nombres
        binding.txtEmailA.text = user!!.correoElectronico

        binding.btnSignout.setOnClickListener {

            if(user!!.idOrigenCuenta.equals("1")){
                //signOutG()
            }
            else if (user!!.idOrigenCuenta.equals("2")){
                //signOutF()
            }
            else{
            }

            viewModel.signout(user!!)
            if (activity is HomeActivity) {
                Toast.makeText((activity as HomeActivity),"Sesion Cerrada", Toast.LENGTH_SHORT).show()

                (activity as HomeActivity).openLogin()
            }

        }

        /*
        viewModel.getAccount().observe(viewLifecycleOwner, Observer {
            it?.let {
                listuser = it

                if (listuser.isNotEmpty()) {

                    binding.txtNameA.text = listuser[0].nombres
                    binding.txtNameP.text = listuser[0].nombres
                    binding.txtEmailA.text = listuser[0].correoElectronico

                    binding.btnSignout.setOnClickListener {

                        if(listuser[0].idOrigenCuenta.equals("1")){
                            //signOutG()
                        }
                        else if (listuser[0].idOrigenCuenta.equals("2")){
                            //signOutF()
                        }
                        else{
                        }

                        viewModel.signout(listuser[0])
                        if (activity is HomeActivity) {
                            (activity as HomeActivity).openLogin()
                        }

                    }


                }
                else {


                }
            }
        })

         */



    }


}