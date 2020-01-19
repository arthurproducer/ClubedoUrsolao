package br.com.arthur.clubedoursolao.view.list


import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.model.LendingProduct
import br.com.arthur.clubedoursolao.model.User
import kotlinx.android.synthetic.main.dialog_update_my_product.*
import kotlinx.android.synthetic.main.dialog_update_my_product.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class UpdateMyProductDialogFragment : DialogFragment(), View.OnClickListener {

    private val updateMyProductViewModel: UpdateMyProductViewModel by viewModel()
    private val preferences: SharedPreferences by inject()

    lateinit var lendingProduct: LendingProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_update_my_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setValues()
//        edtName.hint = navArgs<UpdateMyProductDialogFragmentArgs>().value.title
        edtName.hint = navArgs<UpdateMyProductDialogFragmentArgs>().value.Test
        lendingProduct = navArgs<UpdateMyProductDialogFragmentArgs>().value.lendingProduct

        edtName.setText(navArgs<UpdateMyProductDialogFragmentArgs>().value.lendingProduct.title)
        //edtName.hint = navArgs<UpdateMyProductDialogFragmentArgs>().value.title
        edtLeanderName.setText(navArgs<UpdateMyProductDialogFragmentArgs>().value.lendingProduct.owner_lending)
        edtEndDate.setText(navArgs<UpdateMyProductDialogFragmentArgs>().value.lendingProduct.returnDate)


        radioBtnStatusTrue.setOnClickListener(this)

        updateMyProductViewModel.messageResponse.observe(
            this, Observer {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            })

        edtEndDate.setOnEditorActionListener { _, i, _ ->
            handleKeyboardEvent(i)
        }
        dialog?.setTitle(R.string.updateMyProduct)
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun handleKeyboardEvent(actionId: Int): Boolean{
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            update()
            return true
        }
        return false }

    private fun update(){

//        lendingProduct.situation = radioGroupStatusBorrowed.checkedRadioButtonId
        lendingProduct.title = edtName.text.toString()
        lendingProduct.owner_lending = edtLeanderName.text.toString()
//        lendingProduct.returnDate = edtEndDate.text.toString()

//        val user = User()
//        user.id = preferences.getInt("ID", 0)
        updateMyProductViewModel.updateMyProduct(lendingProduct)
    }

    override fun onClick(p0: View?) {
        edtName.imeOptions = EditorInfo.IME_ACTION_NEXT
        edtLeanderName.isEnabled = true
        edtEndDate.isEnabled = true
    }
}

