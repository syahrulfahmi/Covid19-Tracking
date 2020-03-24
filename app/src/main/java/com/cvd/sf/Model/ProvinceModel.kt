package com.cvd.sf.Model
import com.google.gson.annotations.SerializedName


/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 24/03/20
 */

data class ProvinceModel(
    @SerializedName("attributes")
    val attributes: Attributes
)

data class Attributes(
    @SerializedName("FID")
    val fID: Int,
    @SerializedName("Kasus_Meni")
    val kasusMeni: Int,
    @SerializedName("Kasus_Posi")
    val kasusPosi: Int,
    @SerializedName("Kasus_Semb")
    val kasusSemb: Int,
    @SerializedName("Kode_Provi")
    val kodeProvi: Int,
    @SerializedName("Provinsi")
    val provinsi: String
)