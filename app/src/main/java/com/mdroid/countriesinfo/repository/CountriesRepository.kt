package com.mdroid.countriesinfo.repository

import com.mdroid.countriesinfo.retrofit.RetroAPIInterface

/***
 * Main Repository class for getting all countries
 */
class CountriesRepository constructor(private val retrofitService: RetroAPIInterface) {

    suspend fun getAllCountries() = retrofitService.getCountriesFromAPI()

}