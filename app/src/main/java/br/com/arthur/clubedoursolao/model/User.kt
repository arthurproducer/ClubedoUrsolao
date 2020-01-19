package br.com.arthur.clubedoursolao.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Keep
@Parcelize
data class User (
    var id : Int,
    var name : String,
    var email : String,
    var password : String,
    var countrycode : Int,
    var arecode : Int,
    var phone : String,
    var zipcode : String
):Parcelable {
    constructor() : this(0,"","","",0,0,"","")
}
