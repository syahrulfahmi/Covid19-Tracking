package com.cvd.sf.Model
import com.google.gson.annotations.SerializedName


/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */
data class CountryCasesModel(
    @SerializedName("confirmed")
    val confirmed: Confirmed,
    @SerializedName("deaths")
    val deaths: Deaths,
    @SerializedName("lastUpdate")
    val lastUpdate: String,
    @SerializedName("recovered")
    val recovered: Recovered
)