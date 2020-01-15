package br.com.arthur.clubedoursolao.view.category


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_shortcut_category.*
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.row_cardview_category.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment() {

    val listCategoryViewModel : CategoryViewModel by viewModel()
    val picasso: Picasso by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listCategoryViewModel.getProducts()

//        listCategoryViewModel.isLoading.observe(this, Observer {
//            if(it == true) {
//                containerLoading.visibility = View.VISIBLE
//            } else {
//                containerLoading.visibility = View.GONE
//            }
//        })
//
        listCategoryViewModel.messageError.observe(this, Observer {
            if(it != "") {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                Log.d("CATEGORYFRAGMENT_ERROR",it)
            }
        })

        listCategoryViewModel.products.observe(this, Observer {
            categoryRecyclerView.adapter = CategoryAdapter(it,picasso)
            categoryRecyclerView.layoutManager = LinearLayoutManager(context)
        })

        //val listProducts = getMyList()
        //val adapter = CategoryAdapter(listProducts)

//        categoryRecyclerView.adapter = adapter
//
//        val layoutManager = LinearLayoutManager(context)
//
//        categoryRecyclerView.layoutManager = layoutManager

        if (btnDetalhes == null) {

        }else{
            btnDetalhes.setOnClickListener {
                it.findNavController().navigate(R.id.action_item_myProducts_to_productDetailsFragment)
            }
        }
    }
//
//    private fun getMyList() : ArrayList<Product>{
//
//        val prod = ArrayList<Product>()
//        prod.add(Product("Foice",R.color.colorNegativeStatus,R.drawable.logo_ursolao_light,"Rua da Paz","Ana","14/10/2019"))
//        prod.add(Product("Foice",R.color.colorNegativeStatus,R.drawable.logo_ursolao_light,"Rua da Paz","Ana","14/10/2019"))
//        prod.add(Product("Foice",R.color.colorNegativeStatus,R.drawable.logo_ursolao_light,"Rua da Paz","Ana","14/10/2019"))
//        prod.add(Product("Foice",R.color.colorNegativeStatus,R.drawable.logo_ursolao_light,"Rua da Paz","Ana","14/10/2019"))
//
//        return prod
//    }


}
