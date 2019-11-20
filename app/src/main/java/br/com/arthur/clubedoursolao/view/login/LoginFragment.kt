package br.com.arthur.clubedoursolao.view.login


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import br.com.arthur.clubedoursolao.LoginActivity
import br.com.arthur.clubedoursolao.MainActivity
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.User
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


/**
 * A simple [Fragment] subclass.
 *
 */

class LoginFragment : Fragment() {

    val loginViewModel : LoginViewModel by viewModel()

    //lateinit var user : User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //backgroundLayout.getBackground().setAlpha(80)  // here the value is an integer not float

        loginViewModel.messageError.observe(this, Observer {
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
        })


        registerLink.setOnClickListener {
            it.findNavController()?.navigate(R.id.action_loginFragment_to_registerUserFragment)
        }
        btnLogar.setOnClickListener {view ->
            val user = User()
            user.email = edtEmail.text.toString()
            user.password = edtPassword.text.toString()

            loginViewModel.checkAuth(user)
            loginViewModel.token.observe(this, Observer {token ->
                Toast.makeText(context,token.token,Toast.LENGTH_LONG).show()
                //Linha abaixo só será utilizada quando pegar o token
                view.findNavController()?.navigate(R.id.action_loginFragment_to_myProductFragment)
            })
        }
    }

}
