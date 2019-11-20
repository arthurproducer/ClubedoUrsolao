package br.com.arthur.clubedoursolao.model

import com.google.gson.annotations.SerializedName


data class TokenResponse(
    @SerializedName( "token") val token : String
)