package com.mdroid.countriesinfo.utils

import com.mdroid.countriesinfo.model.CountriesModel

object ValidationUtil {

    fun validateACountry(country: CountriesModel): Boolean {
        if (country.name.common?.isNotEmpty() == true) {
            return true
        }
        return false
    }
}