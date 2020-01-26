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
data class InsertProduct(
    @SerializedName("title") var title: String,
    @SerializedName("descr") var descr: String,
    @SerializedName("photo") var photo: String,
    @SerializedName("category") var category: Int,
    @SerializedName("Owner_ID") var owner_id: Int
) : Parcelable {
    constructor() : this( "", "", "",0, 0)
}
