package br.com.arthur.clubedoursolao.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.LendingProduct
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lender_loan_date.view.*
import kotlinx.android.synthetic.main.row_cardview_devolution.view.*

class DevolutionAdapter(
    private val listlendingProducts: List<LendingProduct>,
    val picasso: Picasso,
    val longclickListener: (LendingProduct) -> Unit) : RecyclerView.Adapter<VHLendingProductDevolution>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHLendingProductDevolution {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_cardview_devolution,parent,false)
        val vh = VHLendingProductDevolution(v)

        return vh
    }

    override fun getItemCount(): Int = listlendingProducts.size

    override fun onBindViewHolder(holder: VHLendingProductDevolution, position: Int) {
        val lendingProduct = listlendingProducts[position]
        holder.bindView(lendingProduct,picasso,longclickListener)
    }

}

class VHLendingProductDevolution(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(
        lendingProduct: LendingProduct,
        picasso: Picasso,
        longclickListener: (LendingProduct) -> Unit) = with(itemView) {
        if (lendingProduct.status.equals("Enabled")) {
            backgroundStatusProduct.setColorFilter(R.color.colorPositiveStatus)
        } else {
            backgroundStatusProduct.setColorFilter(R.color.colorNegativeStatus)
        }

        txtCardTitle.text = lendingProduct.title
        if(lendingProduct.situation == 1){
            txtLocation.text = "Pego emprestado com:"
            txtLenderName.text = lendingProduct.owner_lending
            txtLoanDate.text = lendingProduct.returnDate

        }else{
            txtLocation.text = "Tinha nem que estar aqui!!!"
            view_lender_loan_date.visibility = View.GONE
        }

        setOnClickListener{longclickListener(lendingProduct)}
    }
}