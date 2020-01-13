package br.com.arthur.clubedoursolao.model


import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel
data class LendingProduct(
    var id : Int,
    var title : String,
    var address: String,
    var photo: Int,
    var status : Int,
    var owner_id : Int,
    var owner_name : String,
    var returnDate: String){

    @ParcelConstructor constructor() : this(0,"","",0,0,0,"","")
}