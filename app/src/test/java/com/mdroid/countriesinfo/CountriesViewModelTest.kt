package com.mdroid.countriesinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mdroid.countriesinfo.model.CountriesModel
import com.mdroid.countriesinfo.repository.CountriesRepository
import com.mdroid.countriesinfo.retrofit.RetroAPIInterface
import com.mdroid.countriesinfo.viewmodel.CountriesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CountriesViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var countriesViewModel: CountriesViewModel
    lateinit var countriesRepository: CountriesRepository

    @Mock
    lateinit var retroAPIInterface: RetroAPIInterface

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        countriesRepository = CountriesRepository(retroAPIInterface)
        countriesViewModel = CountriesViewModel(countriesRepository)
    }

    @Test
    fun `empty place list test`() {
        runBlocking {
            Mockito.`when`(countriesRepository.getAllCountries())
                .thenReturn(Response.success(listOf<CountriesModel>()))
            countriesViewModel.getAllCountries()
            val result = countriesViewModel.countriesList.getOrAwaitValue()
            assertEquals(listOf<CountriesModel>(), result)
        }
    }

}