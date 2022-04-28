package com.mdroid.countriesinfo.retrofit

import com.mdroid.countriesinfo.model.CountriesModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * A common Retrofit Interface to handle the API calling and provide the instance of Retrofit back to caller
 * @author MayuR
 */
interface RetroAPIInterface {

    //https://restcountries.com/v3.1/region/europe

    //@GET("v3.1/region/europe")
    @GET("v3.1/all")
    suspend fun getCountriesFromAPI(): Response<List<CountriesModel>>

    companion object {

        //Base URL for the app (without the method)
        private const val BASE_URL = "https://restcountries.com/" //v3.1/all

        var retrofitService: RetroAPIInterface? = null

        fun getInstance(): RetroAPIInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetroAPIInterface::class.java)
            }
            return retrofitService!!
        }
    }
}
