package com.mdroid.countriesinfo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mdroid.countriesinfo.repository.CountriesRepository

class BaseViewModelFactory constructor(private val repository: CountriesRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            CountriesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel exception")
        }
    }
}