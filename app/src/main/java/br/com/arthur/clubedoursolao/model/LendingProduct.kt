package br.com.arthur.clubedoursolao.model


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Keep
@Parcelize
data class LendingProduct(
    @SerializedName("Lending_ID") var id: Int,
    @SerializedName("Item_ID") var product_id:Int,
    @SerializedName("Title") var title: String,
//    @SerializedName("Address") var address: String,
    @SerializedName("Status_Borrowed") var situation: Int, //1 for true e 0 for false
    @SerializedName("Owner_lending") var owner_lending: String,
    @SerializedName("EndDate_lending") var returnDate: String,
    @SerializedName("Photo") var photo: String,
    @SerializedName("Status") var status: String
):Parcelable
//{
//    @ParcelConstructor
//    constructor() : this(0, "", 0, "", "", "", "")
//}