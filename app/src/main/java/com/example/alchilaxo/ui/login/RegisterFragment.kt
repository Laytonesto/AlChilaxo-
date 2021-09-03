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
import com.example.alchilaxo.databinding.FragmentRegisterBinding
import com.example.alchilaxo.domain.entities.UserDataObject
import com.example.alchilaxo.ui.HomeActivity
import com.example.alchilaxo.ui.LoginActivity
import com.example.alchilaxo.viewmodel.LoginActivityViewModel
import com.example.alchilaxo.viewmodel.LoginActivityViewModelFactory
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception


class RegisterFragment : Fragment(), DefaultFlow {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initFlow()

        // Inflate the layout for this fragment
        return root
    }

    override fun initFlow() {

        viewModel = ViewModelProvider(this, LoginActivityViewModelFactory()).get(
            LoginActivityViewModel::class.java
        )

        var nameValue = binding.edtNombre.text.toString()
        var numValue = binding.edtCel.text.toString()
        var emailValue = binding.edtEmail.text.toString()
        var passwordValue = binding.edtPassword.text.toString()
        var cpasswordValue = binding.edtCPassword.text.toString()
        var genero: String?


        binding.btnCrearCuenta.setOnClickListener {
            it?.let {


                nameValue = binding.edtNombre.text.toString()
                numValue = binding.edtCel.text.toString()
                emailValue = binding.edtEmail.text.toString()
                passwordValue = binding.edtPassword.text.toString()
                cpasswordValue = binding.edtCPassword.text.toString()

                if (nameValue.isNotEmpty()) {
                    binding.ledtNombre.error = null
                        if (numValue.isNotEmpty()) {
                            binding.ledtCel.error = null
                            if (emailValue.isNotEmpty()) {
                                binding.ledtEmail.error = null
                                if (passwordValue.isNotEmpty()) {
                                    binding.ledtPassword.error = null
                                    if (cpasswordValue.isNotEmpty()) {
                                        binding.ledtCPassword.error = null
                                        if (cpasswordValue == passwordValue) {
                                            binding.ledtCPassword.error = null


                                            val datos = UserDataObject(emailValue,nameValue,"",0, numValue, "M",0,passwordValue)


                                            viewModel.generateRegister(datos)

                                            viewModel.getLoginDataOptions().observe(viewLifecycleOwner, Observer {
                                                it?.let {

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
                                                        if (activity is LoginActivity){
                                                        Toast.makeText((activity as LoginActivity),"Datos incorrectos, Inténtelo de nuevo", Toast.LENGTH_SHORT).show()
                                                    }
                                                    }

                                                }
                                            })




                                        }
                                        else{
                                            binding.ledtCPassword.error = getString(R.string.nocoincide)
                                        }

                                    }
                                    else {
                                        binding.ledtCPassword.error = getString(R.string.requiredField)
                                    }


                                }
                                else {
                                    binding.ledtPassword.error = getString(R.string.requiredField)
                                }


                            }
                            else {
                                binding.ledtEmail.error = getString(R.string.requiredField)
                            }


                        }
                        else {
                            binding.ledtCel.error = getString(R.string.requiredField)
                        }


                }
                else {
                    binding.ledtNombre.error = getString(R.string.requiredField)
                }
            }
        }



    }


}