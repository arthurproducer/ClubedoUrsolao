package br.com.arthur.clubedoursolao.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun registerMyNewProduct(product: Product){

        isLoading.value = true
        productRepository.registerMyNewProduct(product,{
            isLoading.value = false
//            lendingProducts.value = it
            messageResponse.value = "Dados gravados com sucesso"
        },{
            //            lendingProducts.value = emptyList()
            isLoading.value = false
            messageResponse.value = it?.message
        })

    }
}