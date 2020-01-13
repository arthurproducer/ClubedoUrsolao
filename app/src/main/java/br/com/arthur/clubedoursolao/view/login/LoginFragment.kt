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


/**
 * A simple [Fragment] subclass.
 *
 */

class LoginFragment : Fragment(), CoroutineScope {

    val loginViewModel : LoginViewModel by viewModel()
    private val preferences : SharedPreferences by inject()
    lateinit var authInterceptor : AuthInterceptor


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
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

        registerLink.setOnClickListener {
            it.findNavController() ?.navigate(R.id.action_loginFragment_to_registerUserFragment)
        }
        btnLogar.setOnClickListener {view ->
            val user = User()
            user.email = edtEmail.text.toString()
            user.password = edtPassword.text.toString()
            //user.password = codificarBase64(edtPassword.text.toString())

            loginViewModel.checkAuth(user)
            loginViewModel.token.observe(this, Observer {token ->
                Toast.makeText(context,token.token,Toast.LENGTH_LONG).show()

//                val pref = context?.getSharedPreferences("Token",0)
                val editor = preferences.edit()
                editor?.putString("Token",token.token)
                editor?.apply()

                launch{
                    coroutineScope {
                        withContext(Dispatchers.Default){authInterceptor = AuthInterceptor(preferences)}
                        withContext(Dispatchers.Default){ view.findNavController().navigate(R.id.action_loginFragment_to_myMainActivity) }
                    }
                }
                authInterceptor = AuthInterceptor(preferences)

                Log.d("Token", token.token)
                //Linha abaixo só será utilizada quando pegar o token
            })
        }

        loginViewModel.messageError.observe(this, Observer {
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
        })

    }

}
