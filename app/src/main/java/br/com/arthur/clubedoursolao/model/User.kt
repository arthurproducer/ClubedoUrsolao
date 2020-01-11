package br.com.arthur.clubedoursolao.model

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel
data class User (
    var name : String,
    var email : String,
    var password : String,
    var countrycode : Int,
    var arecode : Int,
    var phone : String,
    var zipcode : String
){
    @ParcelConstructor constructor() : this("","","",0,0,"","")
}
