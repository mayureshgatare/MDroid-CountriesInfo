package com.mdroid.countriesinfo.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mdroid.countriesinfo.R
import com.mdroid.countriesinfo.databinding.ActivityCountryDetailsBinding
import com.mdroid.countriesinfo.model.CountriesModel


class CountryDetailsActivity : AppCompatActivity() {

    lateinit var countriesModel: CountriesModel
    lateinit var binding: ActivityCountryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_country_details)

        getDataFromBundle()
        initViews()
        setDataOnUI()
    }

    /**
     *
     */
    private fun getDataFromBundle() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            countriesModel = intent.getSerializableExtra("model") as CountriesModel
        }
    }

    /**
     *
     */
    private fun initViews() {
        binding = ActivityCountryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setDataOnUI() {
        if (countriesModel!=null) {

            val descriptionText: String = String.format(resources.getString(R.string.country_description_text)
                , countriesModel.name.common
                , countriesModel.name.official
                , countriesModel.region
                , countriesModel.subregion
            )

            val demographicText: String = String.format(resources.getString(R.string.country_demography_text)
                , countriesModel.name.official
                , countriesModel.area
                , countriesModel.population
                , countriesModel.car.side
            )

            val capital: String = String.format(resources.getString(R.string.country_capital)
                , countriesModel.capital[0]
            )

            binding.txtDescriptionDetails.text = descriptionText
            binding.txtDemographyDetails.text = demographicText
            binding.txtCountryName.text = countriesModel.name.common
            binding.txtCapital.text = capital
            //binding.txtTextTwo.text = countriesModel.name.official

            if (null!=countriesModel.coatOfArms.png) {
                //For the countries emblem
                Glide.with(this@CountryDetailsActivity).load(Uri.parse(countriesModel.coatOfArms.png)).into(binding.imgEmblem)

            }

            binding.btnGoogleMap.setOnClickListener (View.OnClickListener {

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(countriesModel.maps.googleMaps)
                )
                startActivity(intent)
            })
        }
    }
}