package com.cvd.sf.Model
import com.google.gson.annotations.SerializedName


/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 21/03/20
 */

data class ConfirmedCasesModel(
    @SerializedName("active")
    val active: Int,
    @SerializedName("admin2")
    val admin2: Any,
    @SerializedName("combinedKey")
    val combinedKey: Any,
    @SerializedName("confirmed")
    val confirmed: Int,
    @SerializedName("countryRegion")
    val countryRegion: String,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("fips")
    val fips: Any,
    @SerializedName("iso2")
    val iso2: String,
    @SerializedName("iso3")
    val iso3: String,
    @SerializedName("lastUpdate")
    val lastUpdate: Long,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("long")
    val long: Double,
    @SerializedName("provinceState")
    val provinceState: String?,
    @SerializedName("recovered")
    val recovered: Int
)