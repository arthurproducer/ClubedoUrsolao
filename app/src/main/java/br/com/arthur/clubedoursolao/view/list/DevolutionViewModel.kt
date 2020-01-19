package br.com.arthur.clubedoursolao.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.model.DevolutionResponse
import br.com.arthur.clubedoursolao.model.LendingProduct
import br.com.arthur.clubedoursolao.model.User
import br.com.arthur.clubedoursolao.repository.AuthRepository
import br.com.arthur.clubedoursolao.repository.ProductRepository

class DevolutionViewModel (val productRepository: ProductRepository) : ViewModel() {

    val messageError: MutableLiveData<String> = MutableLiveData()
    val lendingProducts: MutableLiveData<List<LendingProduct>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getMyDevolutionProducts(id: User) {
        isLoading.value = true
        productRepository.getMyDevolutionProducts(id,
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

    fun returnDevolutionProduct(devolutionResponse: DevolutionResponse){
        productRepository.returnDevolutionProduct(devolutionResponse,{
            messageError.value = "Produto devolvido com sucesso!"
        }, {
            messageError.value = it?.message
        })
    }
}