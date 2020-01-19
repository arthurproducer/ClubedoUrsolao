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

    @GET("/items/category/Clothes")
    fun getForCategory() :Call<List<Product>>

    @GET("/lendings/clients/{id}")
    fun getMyProducts(@Path("id") id: Int) : Call<List<LendingProduct>>

    @PUT("/lendings/clients")
    fun updateMyProducts(@Body lendingProduct: LendingProduct) : Call<LendingProduct>


//    @GET("/api/pokemon")
//    fun getPokemons(
//        @Query("size") size: Int,
//        @Query("sort") sort: String
//    ): Call<PokemonResponse>
//
//    @PUT("/api/pokemon")
//    fun updatePokemon(@Body pokemon: Pokemon) : Call<Pokemon>
}