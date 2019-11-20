package br.com.arthur.clubedoursolao.repository

import br.com.arthur.clubedoursolao.api.Api
import br.com.arthur.clubedoursolao.model.AuthResponse
import br.com.arthur.clubedoursolao.model.HealthResponse
import br.com.arthur.clubedoursolao.model.TokenResponse
import br.com.arthur.clubedoursolao.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepositoryImpl(val api : Api): AuthRepository{

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

}