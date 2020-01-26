package br.com.arthur.clubedoursolao.view.list


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer

import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.InsertProduct
import br.com.arthur.clubedoursolao.model.Product
import br.com.arthur.clubedoursolao.model.User
import kotlinx.android.synthetic.main.dialog_register_my_new_product_dialog.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterMyNewProductDialogFragment : DialogFragment() {
    private val registerMyNewProductViewModel: RegisterMyNewProductViewModel by viewModel()
    private val preferences: SharedPreferences by inject()

    val product =  InsertProduct()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_register_my_new_product_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = User()
        user.id = preferences.getInt("ID", 0)

        //Tratar os dados digitados pelo usuário

        product.title = edtTitle.text.toString()
        product.descr = edtDescr.text.toString()
        product.category = 3
        product.owner_id = user.id


        registerMyNewProductViewModel.messageResponse.observe(
            this, Observer {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            })

        btnRegisterProduct.setOnClickListener {
            //tratar dados obrigatórios
            registerProduct()}

        dialog?.setTitle(R.string.updateMyProduct)
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun registerProduct(){
        registerMyNewProductViewModel.registerMyNewProduct(product)
    }
}
