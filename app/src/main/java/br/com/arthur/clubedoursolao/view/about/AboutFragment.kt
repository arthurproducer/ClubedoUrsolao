package br.com.arthur.clubedoursolao.view.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.arthur.clubedoursolao.R
import kotlinx.android.synthetic.main.fragment_about.*
import br.com.arthur.clubedoursolao.MainActivity

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var uri : Uri?
        var intent : Intent?

        btnCall.setOnClickListener {
             //Realiza uma chamada
            uri = Uri.parse(edtInvitePhone.text.toString())
            intent = Intent(Intent.ACTION_DIAL,uri)
            //intent = Intent(activity!!.application, MainActivity::class.java)
            startActivity(intent)
        }
        btnShare.setOnClickListener {

            intent = Intent()
                .setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, "Compartilhando a experiência no Clube do Ursolão.")
                .setType("text/plain")
            //intent = Intent(activity!!.application, MainActivity::class.java)
            startActivity(intent)        }
        }
    }
