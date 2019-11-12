package br.com.arthur.clubedoursolao.view.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.arthur.clubedoursolao.R
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 *
 */

class LoginFragment : Fragment() {
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
            it.findNavController()?.navigate(R.id.action_loginFragment_to_registerUserFragment)
        }
    }

}
