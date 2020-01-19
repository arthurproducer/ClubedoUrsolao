package br.com.arthur.clubedoursolao.api

import android.content.SharedPreferences
import br.com.arthur.clubedoursolao.model.*
import br.com.arthur.clubedoursolao.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface Api{

    @GET("/")
    fun checkHealth() : Call<HealthResponse>

    @POST("/auth")
    fun checkAuth(@Body user : User) : Call<TokenResponse>

    @POST("/register")
    fun registerUser(@Body user : User) : Call<User>

    @GET("/items/category/{category}")
    fun getForCategory(@Path("category")category : String) :Call<List<Product>>

    @GET("/items/client/{id}")
    fun getMyProducts(@Path("id") id: Int) : Call<List<LendingProduct>>

    @PUT("/lendings/clients/{id}")
    fun updateMyProducts(@Body lendingProduct: LendingProduct) : Call<LendingProduct>

    //registerMyProducts

    @GET("/lendings/clients/{id}")
    fun getMyDevolutionProducts(@Path("id") id: Int) : Call<List<LendingProduct>>

    @PUT("/lendings/items")
    fun returnDevolutionProduct(@Body devolutionProduct: DevolutionResponse) : Call<LendingProduct>

}