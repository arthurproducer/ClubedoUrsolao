package br.com.arthur.clubedoursolao.view.list


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.DevolutionResponse
import br.com.arthur.clubedoursolao.model.LendingProduct
import br.com.arthur.clubedoursolao.model.Product
import br.com.arthur.clubedoursolao.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_devolution.*
import kotlinx.android.synthetic.main.row_cardview_devolution.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DevolutionFragment : Fragment() {

    private val listMyDevolutionProductViewModel : DevolutionViewModel by viewModel()
    private val preferences: SharedPreferences by inject()
    private val picasso: Picasso by inject()
    private val user = User()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_devolution, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user.id = preferences.getInt("ID", 0)

        listMyDevolutionProductViewModel.getMyDevolutionProducts(user)

        listMyDevolutionProductViewModel.messageError.observe(this, Observer {
            if (it != "") {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        listMyDevolutionProductViewModel.lendingProducts.observe(this, Observer {
            devolutionRecyclerView.adapter = DevolutionAdapter(it, picasso){
                AlertDialog.Builder(context!!)
                    .setTitle("Devolver o ${it.title}")
                    .setMessage("A data de devolução deste produto é ${it.returnDate}. Vai devolver?")
                    .setPositiveButton("Sim") { dialog, which ->
                        val devolution = DevolutionResponse(it.id,it.product_id)
                        listMyDevolutionProductViewModel.returnDevolutionProduct(devolution)
                        listMyDevolutionProductViewModel.getMyDevolutionProducts(user)
                    }
                    .setNegativeButton("Não") { dialog, which ->
                        dialog.cancel()
                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
            devolutionRecyclerView.layoutManager = LinearLayoutManager(context)
        })

    }
}
