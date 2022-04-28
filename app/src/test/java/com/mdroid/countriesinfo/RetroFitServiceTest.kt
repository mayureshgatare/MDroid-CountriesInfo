package com.mdroid.countriesinfo

import com.google.gson.Gson
import com.mdroid.countriesinfo.retrofit.RetroAPIInterface
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: RetroAPIInterface
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(RetroAPIInterface::class.java)
    }


    @Test
    fun `get all countries api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getCountriesFromAPI()
            val request = mockWebServer.takeRequest()
            assertEquals("/v3.1/all", request.path)
            assertEquals(true, response.body()?.isEmpty() == true)
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}