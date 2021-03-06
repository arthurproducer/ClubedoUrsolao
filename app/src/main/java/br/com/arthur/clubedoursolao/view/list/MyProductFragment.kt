package br.com.arthur.clubedoursolao.view.list


import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_my_product.*
import kotlinx.android.synthetic.main.row_cardview_myproducts.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MyProductFragment : Fragment() {

    private val listMyProductViewModel : MyProductViewModel by viewModel()
    private val preferences: SharedPreferences by inject()
    private val picasso: Picasso by inject()
    private var actionMode : ActionMode? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = User()
        user.id = preferences.getInt("ID", 0)

        listMyProductViewModel.getMyProducts(user)

        listMyProductViewModel.messageError.observe(this, Observer {
            if (it != "") {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        listMyProductViewModel.lendingProducts.observe(this, Observer {
            myProductRecyclerView.adapter = MyProductAdapter(it, picasso){

                if(it.situation == 1){
                    Toast.makeText(context,"Item emprestado não pode ser alterado!",Toast.LENGTH_LONG).show()
                }else{
                    val action = MyProductFragmentDirections.actionItemMyProductsToUpdateMyProductFragment(it)
                    view.findNavController().navigate(action)
                }
            }
            myProductRecyclerView.layoutManager = LinearLayoutManager(context)
        })

//        btnAvailableForLoan.setOnClickListener {
//
//        }


        if (btnDetalhes == null) {

        } else {
            btnDetalhes.setOnClickListener {
                it.findNavController().navigate(R.id.action_item_myProducts_to_productDetailsFragment)
            }
        }

        fab.setOnClickListener { view ->
            val action = MyProductFragmentDirections.actionItemMyProductsToRegisterMyNewProductDialogFragment()
            view.findNavController().navigate(action)
        }
    }
//    override fun onItemLongClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long): Boolean {
//
//    }
}
