package com.mdroid.countriesinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdroid.countriesinfo.model.CountriesModel
import com.mdroid.countriesinfo.repository.CountriesRepository
import kotlinx.coroutines.*

/**
 * ViewModel class for all the UI to data handling via the Countries repository
 * @author MayuR
 */
class CountriesViewModel constructor(private val countriesRepository: CountriesRepository) :
    ViewModel() {

    val errorMessage = MutableLiveData<String>()

    //Live data list mutable one
    val countriesList = MutableLiveData<List<CountriesModel>>()

    //To handle the behaviour fo the Co-Routine
    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}") //generic way for exception hand;ling in CoRoutine
    }

    //Temporary used for Loader purposes
    val loading = MutableLiveData<Boolean>()

    fun getAllCountries() { //Will be called in the UI layer, have kept the same name as in API interfaces for now

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = countriesRepository.getAllCountries()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    /*val list: List<CountriesModel>? = response.body()
                    list.sortedBy { }*/
                    countriesList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}