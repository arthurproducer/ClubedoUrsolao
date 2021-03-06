package br.com.arthur.clubedoursolao.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.model.LendingProduct
import br.com.arthur.clubedoursolao.model.User
import br.com.arthur.clubedoursolao.repository.AuthRepository
import br.com.arthur.clubedoursolao.repository.ProductRepository

class UpdateMyProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()
//    val lendingProducts: MutableLiveData<List<LendingProduct>> = MutableLiveData()

    fun updateMyProduct(lendingProduct: LendingProduct){

        isLoading.value = true
        productRepository.updateMyProduct(lendingProduct,{
            isLoading.value = false
//            lendingProducts.value = it
            messageResponse.value = "Dados gravados com sucesso"
        },{
//            lendingProducts.value = emptyList()
            isLoading.value = false
            messageResponse.value = it?.message
        })

    }
//
//    fun loadHotel(id: Long): LiveData<Hotel> {
//        return repository.hotelById(id)
//    }
//
//    fun saveHotel(hotel: Hotel): Boolean {
//        return validator.validate(hotel)
//            .also { validated ->
//                if (validated) repository.save(hotel)
//            }
//    }
}