package br.com.arthur.clubedoursolao.view.category

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.model.Product
import br.com.arthur.clubedoursolao.repository.AuthRepository
import br.com.arthur.clubedoursolao.repository.ProductRepository


class CategoryViewModel (val productRepository: ProductRepository) : ViewModel() {

    val messageError: MutableLiveData<String> = MutableLiveData()
    val products: MutableLiveData<List<Product>> = MutableLiveData()
    val categories: MutableLiveData<String> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getProducts() {
        isLoading.value = true
        productRepository.getForCategory(categories.value.toString(),
            {
                products.value = it
                messageError.value = ""
                isLoading.value = false
            }, {
                products.value = emptyList()
                messageError.value = it?.message
                isLoading.value = false
            }
        )
    }
}