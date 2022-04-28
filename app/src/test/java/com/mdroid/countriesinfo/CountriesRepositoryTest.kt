package com.mdroid.countriesinfo

import com.mdroid.countriesinfo.model.CountriesModel
import com.mdroid.countriesinfo.repository.CountriesRepository
import com.mdroid.countriesinfo.retrofit.RetroAPIInterface
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class CountriesRepositoryTest {

    lateinit var countriesRepository: CountriesRepository

    @Mock
    lateinit var retroAPIInterface: RetroAPIInterface

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        countriesRepository = CountriesRepository(retroAPIInterface)
    }

    @Test
    fun `get all countries test`() {
        runBlocking {
            Mockito.`when`(retroAPIInterface.getCountriesFromAPI())
                .thenReturn(Response.success(listOf<CountriesModel>()))
            val response = countriesRepository.getAllCountries()
            assertEquals(listOf<CountriesModel>(), response.body())
        }
    }
}