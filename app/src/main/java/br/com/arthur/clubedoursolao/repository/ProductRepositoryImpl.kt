package br.com.arthur.clubedoursolao.repository

import br.com.arthur.clubedoursolao.api.Api
import br.com.arthur.clubedoursolao.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepositoryImpl(val api : Api): ProductRepository{

    override fun getForCategory(category : String,onComplete: (List<Product>?) -> Unit, onError: (Throwable?) -> Unit) {

        api.getForCategory(category)
            .enqueue(object : Callback<List<Product>?> {
                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                    if(response.isSuccessful){
                        onComplete(response.body())
                    } else{
                        onError(Throwable("Não foi possível realizar a chamada."))
                    }
                }
            })
    }

    override fun getMyProduct(user: User, onComplete: (List<LendingProduct>?) -> Unit, onError: (Throwable?) -> Unit) {

        api.getMyProducts(user.id)
            .enqueue(object: Callback<List<LendingProduct>>{
                override fun onFailure(call: Call<List<LendingProduct>>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<List<LendingProduct>>, response: Response<List<LendingProduct>>) {
                    if(response.isSuccessful){
                        onComplete(response.body())
                    } else{
                        onError(Throwable("Não foi possível realizar a chamada."))
                    }
                }
            })
    }

    override fun updateMyProduct(
        lendingProduct: LendingProduct,
        onComplete: (LendingProduct?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        api.updateMyProducts(lendingProduct)
            .enqueue(object: Callback<LendingProduct>{
                override fun onFailure(call: Call<LendingProduct>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<LendingProduct>, response: Response<LendingProduct>) {
                    if(response.isSuccessful){
                        onComplete(response.body())
                    } else{
                        onError(Throwable("Não foi possível realizar a chamada. Update Failure ${response.body()} and ${response.errorBody()}"))
                    }
                }
            })
    }

    override fun registerMyNewProduct(
        product: Product,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        api.registerMyNewProducts(product)
            .enqueue(object : Callback<Product> {
                override fun onFailure(call: Call<Product>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if(response.isSuccessful){
                        onComplete()
                    } else{
                        onError(Throwable("Não foi possível registrar um novo produto!"))
                    }
                }
            })
    }

    override fun getMyDevolutionProducts(
        user: User,
        onComplete: (List<LendingProduct>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        api.getMyDevolutionProducts(user.id)
            .enqueue(object: Callback<List<LendingProduct>>{
                override fun onFailure(call: Call<List<LendingProduct>>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<List<LendingProduct>>, response: Response<List<LendingProduct>>) {
                    if(response.isSuccessful){
                        onComplete(response.body())
                    } else{
                        onError(Throwable("Não foi possível realizar a chamada."))
                    }
                }
            })
    }

    override fun returnDevolutionProduct(
        devolutionResponse: DevolutionResponse,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        api.returnDevolutionProduct(devolutionResponse)
            .enqueue(object : Callback<LendingProduct> {
                override fun onFailure(call: Call<LendingProduct>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<LendingProduct>, response: Response<LendingProduct>) {
                    if(response.isSuccessful){
                        onComplete()
                    } else{
                        onError(Throwable("Lista está vazia!"))
                    }
                }
            })
    }
}