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

class CategoryFragment : Fragment(), View.OnClickListener {

    val listCategoryViewModel : CategoryViewModel by viewModel()
    val picasso: Picasso by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onClick(p0: View?) {
        when(p0){
            shortcutClothes -> {
                listCategoryViewModel.categories.value = "Clothes"
                listCategoryViewModel.getProducts()
            }
            shortcutShoes -> {listCategoryViewModel.categories.value ="Shoes"
                listCategoryViewModel.getProducts()
            }
            shortcutToys -> {listCategoryViewModel.categories.value = "Toys"
                listCategoryViewModel.getProducts()
            }
            shortcutTools -> {listCategoryViewModel.categories.value = "Tools"
                listCategoryViewModel.getProducts()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listCategoryViewModel.categories.value = "Clothes"


        shortcutClothes.setOnClickListener(this)
        shortcutToys.setOnClickListener(this)
        shortcutTools.setOnClickListener(this)
        shortcutShoes.setOnClickListener(this)

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
}
