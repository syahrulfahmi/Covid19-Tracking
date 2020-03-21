package com.cvd.sf.Model
import com.google.gson.annotations.SerializedName


/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 21/03/20
 */

data class MainCasesModel(
    @SerializedName("confirmed")
    val confirmed: Confirmed,
    @SerializedName("countries")
    val countries: String,
    @SerializedName("countryDetail")
    val countryDetail: CountryDetail,
    @SerializedName("dailySummary")
    val dailySummary: String,
    @SerializedName("dailyTimeSeries")
    val dailyTimeSeries: DailyTimeSeries,
    @SerializedName("deaths")
    val deaths: Deaths,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastUpdate")
    val lastUpdate: String,
    @SerializedName("recovered")
    val recovered: Recovered,
    @SerializedName("source")
    val source: String
)

data class Confirmed(
    @SerializedName("detail")
    val detail: String,
    @SerializedName("value")
    val value: Int
)

data class CountryDetail(
    @SerializedName("example")
    val example: String,
    @SerializedName("pattern")
    val pattern: String
)

data class DailyTimeSeries(
    @SerializedName("example")
    val example: String,
    @SerializedName("pattern")
    val pattern: String
)

data class Deaths(
    @SerializedName("detail")
    val detail: String,
    @SerializedName("value")
    val value: Int
)

data class Recovered(
    @SerializedName("detail")
    val detail: String,
    @SerializedName("value")
    val value: Int
)