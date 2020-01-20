package br.com.arthur.clubedoursolao.model

import android.location.Location
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import java.util.*

@Keep
@Parcelize
data class Product(
    @SerializedName("ID") var id: Int,
    @SerializedName("Title") var title: String,
    @SerializedName("descr") var descr: String,
    @SerializedName("category") var category: String,
    @SerializedName("Address") var address: String,
    @SerializedName("Photo") var photo: String,
    @SerializedName("Status") var status: String,
    @SerializedName("Owner_ID") var owner_id: Int,
    @SerializedName("Owner_Name") var owner_name: String
) : Parcelable {
    constructor() : this(0, "", "", "", "", "", "", 0, "")
}
