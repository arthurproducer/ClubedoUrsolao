package br.com.arthur.clubedoursolao.model

import android.location.Location
import java.util.*

data class Product(
    var title : String,
    var status : Int,
    var icon : Int,
    var location: String,
    var owner : String,
    var returnDate: Date
    )