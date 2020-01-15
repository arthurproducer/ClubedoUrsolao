package br.com.arthur.clubedoursolao.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.LendingProduct
import br.com.arthur.clubedoursolao.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lender_loan_date.view.*
import kotlinx.android.synthetic.main.row_cardview_myproducts.*
import kotlinx.android.synthetic.main.row_cardview_myproducts.view.*

class MyProductAdapter(

    private val listlendingProducts: List<LendingProduct>,
    val picasso: Picasso
) : RecyclerView.Adapter<VHLendingProduct>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHLendingProduct {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_cardview_myproducts, parent, false)
        val vh = VHLendingProduct(v)

        return vh
    }

    override fun getItemCount(): Int = listlendingProducts.size

    override fun onBindViewHolder(holder: VHLendingProduct, position: Int) {
        val lendingProduct = listlendingProducts[position]
        holder.bindView(lendingProduct, picasso)
    }
}

class VHLendingProduct(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(
        lendingProduct: LendingProduct,
        picasso: Picasso
    ) = with(itemView) {
        if (lendingProduct.status.equals("Enabled")) {
            backgroundStatusProduct.setColorFilter(R.color.colorPositiveStatus)
        } else {
            backgroundStatusProduct.setColorFilter(R.color.colorNegativeStatus)
        }
//                             imgProduct.setImageResource(product.photo)
//        picasso.load("https://pokedexdx.herokuapp.com${product.photo}").into(ivPokemon)
        txtCardTitle.text = lendingProduct.title
        if(lendingProduct.situation == 1){
            txtLocation.text = "Emprestado para:"
            txtLenderName.text = lendingProduct.owner_lending
            txtLoanDate.text = lendingProduct.returnDate

        }else{
            txtLocation.text = "Disponível para empréstimo"
            view_lender_loan_date.visibility = View.GONE
//            txtLenderName.visibility = View.GONE
//            txtLoanDate.visibility = View.GONE

        }
    }
}

