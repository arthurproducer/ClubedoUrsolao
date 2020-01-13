package br.com.arthur.clubedoursolao.view.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_cardview_category.view.*
import okhttp3.OkHttpClient

class CategoryAdapter(
    private val listProducts : List<Product>,
    val picasso: Picasso
//    val clickListener: (Product) -> Unit
) : RecyclerView.Adapter<VHProduct>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHProduct {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_cardview_category,parent,false)
        val vh = VHProduct(v)

        return vh
    }

    override fun getItemCount(): Int = listProducts.size

    override fun onBindViewHolder(holder: VHProduct, position: Int) {
        val product = listProducts[position]
        holder.bindView(product,picasso)
    }

}

class VHProduct(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(product: Product,
                 picasso: Picasso) = with(itemView){
        if(product.status.equals("Enabled")){
            backgroundStatusProduct.setColorFilter(R.color.colorPositiveStatus)
        }else{
            backgroundStatusProduct.setColorFilter(R.color.colorNegativeStatus)
        }
//                             imgProduct.setImageResource(product.photo)
//        picasso.load("https://pokedexdx.herokuapp.com${product.photo}").into(ivPokemon)
                             txtCardTitle.text = product.title
                            txtLocation.text = product.address
                            txtOwner.text = product.owner_name
                 }


}