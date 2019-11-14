package br.com.arthur.clubedoursolao.api

import br.com.arthur.clubedoursolao.model.AuthResponse
import retrofit2.Call
import retrofit2.http.*

interface Api{

    @POST("/auth")
    fun checkAuth() : Call<AuthResponse>

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