package br.com.arthur.clubedoursolao.view.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.model.Product
import br.com.arthur.clubedoursolao.repository.AuthRepository


class CategoryViewModel (val authRepository: AuthRepository) : ViewModel() {

    val messageError: MutableLiveData<String> = MutableLiveData()
    val products: MutableLiveData<List<Product>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getProducts() {
        isLoading.value = true
        authRepository.getForCategory(
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