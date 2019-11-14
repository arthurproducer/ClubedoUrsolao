package br.com.arthur.clubedoursolao.view.list


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.Product
import kotlinx.android.synthetic.main.fragment_my_product.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MyProductFragment : Fragment() {

    //private var listProducts = mutableListOf<Product>()
    //private var adapter = MyProductAdapter(listProducts)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listProducts = getMyList()
        val adapter = MyProductAdapter(listProducts)

        myProductRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(context)

        myProductRecyclerView.layoutManager = layoutManager

    }

    private fun getMyList() : ArrayList<Product>{

        val prod = ArrayList<Product>()
        prod.add(Product("Machado",R.color.colorNegativeStatus,R.drawable.abc_btn_radio_material,"Rua da Paz","Antônio","14/10/2019"))
        prod.add(Product("Machado",R.color.colorNegativeStatus,R.drawable.abc_btn_radio_material,"Rua da Paz","Antônio","14/10/2019"))
        prod.add(Product("Machado",R.color.colorNegativeStatus,R.drawable.abc_btn_radio_material,"Rua da Paz","Antônio","14/10/2019"))
        prod.add(Product("Machado",R.color.colorNegativeStatus,R.drawable.abc_btn_radio_material,"Rua da Paz","Antônio","14/10/2019"))

        return prod
    }


}
