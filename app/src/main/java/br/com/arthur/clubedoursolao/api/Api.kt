package br.com.arthur.clubedoursolao.api

import br.com.arthur.clubedoursolao.model.AuthResponse
import br.com.arthur.clubedoursolao.model.HealthResponse
import br.com.arthur.clubedoursolao.model.TokenResponse
import br.com.arthur.clubedoursolao.model.User
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
//
//    @GET("/api/pokemon")
//    fun getPokemons(
//        @Query("size") size: Int,
//        @Query("sort") sort: String
//    ): Call<PokemonResponse>
//
//    @PUT("/api/pokemon")
//    fun updatePokemon(@Body pokemon: Pokemon) : Call<Pokemon>
}