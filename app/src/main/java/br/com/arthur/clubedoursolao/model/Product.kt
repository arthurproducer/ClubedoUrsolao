package br.com.arthur.clubedoursolao.model

import android.location.Location
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import java.util.*
@Parcel
data class Product(
    @SerializedName("ID") var id : Int,
    @SerializedName("Title") var title : String,
    @SerializedName("Address") var address: String,
    @SerializedName("Photo") var photo: String,
    @SerializedName("Status") var status : String,
    @SerializedName("Owner_ID") var owner_id : Int,
    @SerializedName("Owner_Name") var owner_name : String){

    @ParcelConstructor constructor() : this(0,"","","","",0,"")
}