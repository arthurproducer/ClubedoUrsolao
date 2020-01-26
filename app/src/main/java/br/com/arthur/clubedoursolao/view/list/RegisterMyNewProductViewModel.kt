package br.com.arthur.clubedoursolao.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.model.InsertProduct
import br.com.arthur.clubedoursolao.model.LendingProduct
import br.com.arthur.clubedoursolao.model.Product
import br.com.arthur.clubedoursolao.model.User
import br.com.arthur.clubedoursolao.repository.AuthRepository
import br.com.arthur.clubedoursolao.repository.ProductRepository

class RegisterMyNewProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()
//    val lendingProducts: MutableLiveData<List<LendingProduct>> = MutableLiveData()

    fun registerMyNewProduct(product: InsertProduct){
        isLoading.value = true
        productRepository.registerMyNewProduct(product,{
            isLoading.value = false
            messageResponse.value = "Produto gravado com sucesso"
        },{
            isLoading.value = false
            messageResponse.value = it?.message
        })

    }
}