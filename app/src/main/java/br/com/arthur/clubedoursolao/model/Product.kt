package br.com.arthur.clubedoursolao.model

import android.location.Location
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import java.util.*
@Parcel
data class Product(
    var title : String,
    var status : Int,
    var icon : Int,
    var location: String,
    var owner : String,
    var returnDate: String){
    @ParcelConstructor constructor() : this("",0,0,"","","")
}