package br.com.arthur.clubedoursolao.view.list


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.Product
import kotlinx.android.synthetic.main.fragment_devolution.*
import kotlinx.android.synthetic.main.row_cardview_devolution.*

class DevolutionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_devolution, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listProducts = getMyList()
        val adapter = MyProductAdapter(listProducts)

        devolutionRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(context)

        devolutionRecyclerView.layoutManager = layoutManager

        if (btnDetalhes == null) {

        }else{
            btnDetalhes.setOnClickListener {
                it.findNavController().navigate(R.id.action_item_myProducts_to_productDetailsFragment)
            }
        }

    }

    private fun getMyList() : ArrayList<Product>{

        val prod = ArrayList<Product>()
        prod.add(Product("Machado",R.color.colorNegativeStatus,R.drawable.logo_yellow,"Rua da Paz","Antônio","14/10/2019"))
        prod.add(Product("Machado",R.color.colorNegativeStatus,R.drawable.logo_yellow,"Rua da Paz","Antônio","14/10/2019"))
        prod.add(Product("Machado",R.color.colorNegativeStatus,R.drawable.logo_yellow,"Rua da Paz","Antônio","14/10/2019"))
        prod.add(Product("Machado",R.color.colorNegativeStatus,R.drawable.logo_yellow,"Rua da Paz","Antônio","14/10/2019"))

        return prod
    }



}
