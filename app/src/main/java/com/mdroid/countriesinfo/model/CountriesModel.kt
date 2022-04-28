package com.mdroid.countriesinfo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Data model or Data class
 */
data class CountriesModel(
    var name: Name,
    val flags: Flags,
    val unMember: Boolean,
    val independent: Boolean,
    val maps: Maps,
    val population: Int,
    val car: Car,
    val region: String,
    val subregion: String,
    val area: String,
    val coatOfArms: CoatOfArms,
    val capital: Array<String>
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CountriesModel

        if (!capital.contentEquals(other.capital)) return false

        return true
    }

    override fun hashCode(): Int {
        return capital.contentHashCode()
    }
}

data class Name(
    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null
) : Serializable

data class Flags(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null
) : Serializable

data class Maps(
    @SerializedName("googleMaps") var googleMaps: String? = null,
    @SerializedName("openStreetMaps") var openStreetMaps: String? = null
) : Serializable

data class Car(
    @SerializedName("side") var side: String? = null,
) : Serializable

data class TimeZone(
    var timezones: String? = null,
)

data class CoatOfArms(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null
) : Serializable

