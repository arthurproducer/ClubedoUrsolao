package br.com.arthur.clubedoursolao.model

import com.google.gson.annotations.SerializedName


data class DevolutionResponse(
    @SerializedName( "id") val lending_ID : Int,
    @SerializedName( "item") val item_ID : Int
)