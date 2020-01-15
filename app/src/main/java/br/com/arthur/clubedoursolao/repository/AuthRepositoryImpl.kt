package br.com.arthur.clubedoursolao.repository

import br.com.arthur.clubedoursolao.api.Api
import br.com.arthur.clubedoursolao.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepositoryImpl(val api : Api): AuthRepository{
    override fun registerUser(
        user: User,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit) {
        api.registerUser(user)
            .enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful){
                        onComplete()//Ver o que vem aqui e se esse trecho de código é necessário
                    } else{
                        onError(Throwable("Não foi possível realizar a chamaada."))
                    }
                }
            })
    }

    override fun checkAuth(
         user: User,
         onComplete: (TokenResponse?) -> Unit,
         onError: (Throwable?) -> Unit){
        api.checkAuth(user)
            .enqueue(object: Callback<TokenResponse>{
                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                    if(response.isSuccessful){
                        onComplete(response.body())
                    } else{
                        onError(Throwable("Erro de autenticação"))
                    }
                }
            })
    }

    override fun checkHealth(onComplete: () -> Unit, onError: (Throwable?) -> Unit) {

        api.checkHealth()
            .enqueue(object : Callback<HealthResponse> {
                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<HealthResponse>, response: Response<HealthResponse>) {
                    if(response.isSuccessful){
                        onComplete()
                    } else{
                        onError(Throwable("Não foi possível realizar a chamaada."))
                    }
                }
            })
    }

    override fun getForCategory(onComplete: (List<Product>?) -> Unit, onError: (Throwable?) -> Unit) {

        api.getForCategory()
            .enqueue(object : Callback<List<Product>?> {
                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                    if(response.isSuccessful){
                        onComplete(response.body())
                    } else{
                        onError(Throwable("Não foi possível realizar a chamaada."))
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
}