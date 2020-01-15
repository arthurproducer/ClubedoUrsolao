package br.com.arthur.clubedoursolao.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.model.LendingProduct
import br.com.arthur.clubedoursolao.model.User
import br.com.arthur.clubedoursolao.repository.AuthRepository


class MyProductViewModel (val authRepository: AuthRepository) : ViewModel() {

    val messageError: MutableLiveData<String> = MutableLiveData()
    val lendingProducts: MutableLiveData<List<LendingProduct>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getMyProducts(id: User) {
        isLoading.value = true
        authRepository.getMyProduct(id,
            {
                lendingProducts.value = it
                messageError.value = ""
                isLoading.value = false
            }, {
                lendingProducts.value = emptyList()
                messageError.value = it?.message
                isLoading.value = false
            }
        )
    }
}