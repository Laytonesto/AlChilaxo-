package com.example.alchilaxo.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alchilaxo.R
import com.example.alchilaxo.core.util.DefaultFlow
import com.example.alchilaxo.core.util.log
import com.example.alchilaxo.database.UserRoomModel
import com.example.alchilaxo.databinding.FragmentLoginBinding
import com.example.alchilaxo.databinding.FragmentPerfilBinding
import com.example.alchilaxo.domain.entities.UserDataObject
import com.example.alchilaxo.ui.HomeActivity
import com.example.alchilaxo.ui.LoginActivity
import com.example.alchilaxo.viewmodel.LoginActivityViewModel
import com.example.alchilaxo.viewmodel.LoginActivityViewModelFactory
import com.google.gson.Gson
import java.lang.Exception


class LoginFragment : Fragment(), DefaultFlow {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginActivityViewModel

    private lateinit var loginResult : UserDataObject




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initFlow()


        return root
    }


    override fun initFlow() {


        viewModel = ViewModelProvider(this, LoginActivityViewModelFactory()).get(
            LoginActivityViewModel::class.java
        )


        binding.btnRegister.setOnClickListener {
            //signOutF()
            //signOutG()
            if (activity is LoginActivity) {
                (activity as LoginActivity).openRegister()
            }

        }


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
                        binding.inputPasswordLayout.error = getString(R.string.requiredField)
                    }
                }
                else {
                    binding.inputUserLayout.error = getString(R.string.requiredField)
                }
            }
        }

        viewModel.getLoginDataOptions().observe(viewLifecycleOwner, Observer {
            it?.let {
                loginResult = it
                try {


                    val user = UserRoomModel(
                        it.correoElectronico,
                        it.nombre,
                        it.deviceID,
                        it.tipo,
                        it.numeroTelefono,
                        it.genero,
                        it.idOrigenCuenta,
                        it.clave
                    )



                    viewModel.saveUser(user)
                    if (activity is LoginActivity) {
                        (activity as LoginActivity).finish()
                    }
                }
                catch (e : Exception){
                    log("Error: ${e.localizedMessage}")
                    if (activity is LoginActivity) {
                        Toast.makeText((activity as LoginActivity),"Datos incorrectos, Inténtelo de nuevo", Toast.LENGTH_SHORT).show()

                    }
                }

            }
        })

        viewModel.fetchEmail().observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.inputUserLayout.error = getString(R.string.emailError)

                } else {

                    binding.inputPasswordLayout.error = null


                }
            }
        })

        viewModel.fetchClave().observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.inputPasswordLayout.error = getString(R.string.claveError)

                } else {

                    binding.inputPasswordLayout.error = null
                    if (activity is LoginActivity) {
                        Toast.makeText((activity as LoginActivity),"Sesion iniciada", Toast.LENGTH_SHORT).show()

                    }


                }
            }
        })

        viewModel.fetchLoadLogin().observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.loginButtonLoading.visibility = View.VISIBLE
                    binding.loginButton.visibility = View.GONE
                    binding.inputUserText.isEnabled = false
                    binding.inputPasswordText.isEnabled = false
                } else {

                    binding.loginButtonLoading.visibility = View.GONE
                    binding.loginButton.visibility = View.VISIBLE
                    binding.inputUserText.isEnabled = true
                    binding.inputPasswordText.isEnabled = true
                }
            }
        })
    }



}