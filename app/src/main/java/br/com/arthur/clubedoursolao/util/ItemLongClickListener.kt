package br.com.arthur.clubedoursolao.util

import android.view.View
import br.com.arthur.clubedoursolao.model.LendingProduct

interface ItemLongClickListener {

   fun onItemLongClick(lendingProduct: LendingProduct)

}