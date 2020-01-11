package br.com.arthur.clubedoursolao.view.registerUser


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.User
import kotlinx.android.synthetic.main.fragment_register_user.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterUserFragment : Fragment() {

    val registerUserViewModel : RegisterUserViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerUserViewModel.messageError.observe(this, Observer {
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
        })

        btnRegister.setOnClickListener {view ->
            val user = User()
            user.name = edtName.text.toString()
            user.email = edtEmail.text.toString()
            user.phone = edtPhone.text.toString()
            user.password = edtPassword.text.toString()
            //user.password = codificarBase64(edtPassword.text.toString())

            registerUserViewModel.registerUser(user)
                //Linha abaixo só será utilizada quando pegar o token

                view.findNavController().navigate(R.id.action_registerUserFragment_to_loginFragment)
            }
        }
        
    }
