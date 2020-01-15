package br.com.arthur.clubedoursolao.view.login


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.api.AuthInterceptor
import br.com.arthur.clubedoursolao.model.User
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()
    private val preferences: SharedPreferences by inject()
    lateinit var authInterceptor: AuthInterceptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerLink.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerUserFragment)
        }
        btnLogar.setOnClickListener { view ->
            val user = User()
            user.email = edtEmail.text.toString()
            user.password = edtPassword.text.toString()
            //user.password = codificarBase64(edtPassword.text.toString())

            loginViewModel.checkAuth(user)
            loginViewModel.token.observe(this, Observer { token ->
                Toast.makeText(context, token.token, Toast.LENGTH_LONG).show()

//                val pref = context?.getSharedPreferences("Token",0)
                val editor = preferences.edit()
                editor?.putString("Token", token.token)
                editor?.putInt("ID",token.id)
                editor?.apply()

                authInterceptor = AuthInterceptor(preferences)
                edtEmail.text = null
                edtPassword.text = null

                view.findNavController().navigate(R.id.action_loginFragment_to_myMainActivity)
            })
        }

        loginViewModel.messageError.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

    }

}
