package br.com.arthur.clubedoursolao.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.Product
import kotlinx.android.synthetic.main.lender_loan_date.view.*
import kotlinx.android.synthetic.main.row_cardview_myproducts.view.*

class DevolutionAdapter(
    private val listProducts : ArrayList<Product>
) : RecyclerView.Adapter<VHProductDevolution>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHProductDevolution {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_cardview_devolution,parent,false)
        val vh = VHProductDevolution(v)

        return vh
    }

    override fun getItemCount(): Int = listProducts.size

    override fun onBindViewHolder(holder: VHProductDevolution, position: Int) {
        val product = listProducts[position]
        holder.statusProduct.setColorFilter(product.status)
        holder.imgProduct.setImageResource(product.icon)
        holder.txtCardTitle.text = product.title
        holder.txtLocation.text = product.location
        holder.tenantProduct.text = product.owner
        holder.txtLoanDate.text = product.returnDate
    }

}

class VHProductDevolution(itemView: View) : RecyclerView.ViewHolder(itemView){
    val statusProduct : ImageView = itemView.backgroundStatusProduct
    val imgProduct : ImageView = itemView.imgProduct
    val txtCardTitle: TextView = itemView.txtCardTitle
    val txtLocation : TextView = itemView.txtLocation
    val tenantProduct : TextView = itemView.txtLenderName
    val txtLoanDate : TextView = itemView.txtLoanDate
}