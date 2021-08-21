package com.example.alchilaxo.ui.sidemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alchilaxo.R
import com.example.alchilaxo.databinding.FragmentNotificationsBinding
import com.example.alchilaxo.databinding.FragmentPerfilBinding
import com.example.alchilaxo.ui.notifications.NotificationsViewModel
import com.example.alchilaxo.viewmodel.LoginActivityViewModel
import com.google.firebase.firestore.FirebaseFirestore


class PerfilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentPerfilBinding? = null
    private lateinit var viewModel: LoginActivityViewModel


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

        binding.loginButton.setOnClickListener {
            it?.let {
                val userValue = binding.inputUserText.text.toString()
                val passValue = binding.inputPasswordText.text.toString()

                if (userValue.isNotEmpty()) {
                    binding.inputUserLayout.error = null

                    if (passValue.isNotEmpty()) {
                        binding.inputPasswordLayout.error = null

                        viewModel.generateLogin(userValue, passValue)
                    }
                    else {
                        //binding.inputPasswordLayout.error = getString(com.facebook.R.string.requiredField)
                    }
                }
                else {
                    //binding.inputUserLayout.error = getString(com.facebook.R.string.requiredField)
                }
            }
        }



        return root
    }


}