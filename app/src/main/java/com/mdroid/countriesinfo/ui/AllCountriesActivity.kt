package com.mdroid.countriesinfo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mdroid.countriesinfo.adapter.CountriesAdapter
import com.mdroid.countriesinfo.adapter.OnClickListener
import com.mdroid.countriesinfo.databinding.ActivityAllCountriesBinding
import com.mdroid.countriesinfo.model.CountriesModel
import com.mdroid.countriesinfo.repository.CountriesRepository
import com.mdroid.countriesinfo.retrofit.RetroAPIInterface
import com.mdroid.countriesinfo.utils.NetworkUtils
import com.mdroid.countriesinfo.viewmodel.BaseViewModelFactory
import com.mdroid.countriesinfo.viewmodel.CountriesViewModel

class AllCountriesActivity : AppCompatActivity() {
    lateinit var viewModel: CountriesViewModel
    lateinit var countriesAdapter: CountriesAdapter
    lateinit var binding: ActivityAllCountriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        initViewModel()
        initListeners()
    }

    private fun initViews() {
        binding = ActivityAllCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViewModel() {
        val retrofitService = RetroAPIInterface.getInstance()
        val mainRepository = CountriesRepository(retrofitService)

        viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(mainRepository)
        ).get(CountriesViewModel::class.java)
        viewModel.countriesList.observe(this) { it ->
            val list: List<CountriesModel> = it.sortedBy { it.name.common }
            countriesAdapter.setCountries(list)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        if (NetworkUtils.isConnected(this)) {
            //Making the API call
            viewModel.getAllCountries()
        } else {
            Toast.makeText(this, "Not connected to Internet!!", Toast.LENGTH_SHORT).show()
            binding.progressDialog.visibility = View.GONE
        }
    }

    private fun initListeners() {
        countriesAdapter = CountriesAdapter(OnClickListener {

            val intent = Intent(this@AllCountriesActivity, CountryDetailsActivity::class.java)
            intent.putExtra("model", it)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)

        })
        binding.recyclerview.adapter = countriesAdapter
    }
}