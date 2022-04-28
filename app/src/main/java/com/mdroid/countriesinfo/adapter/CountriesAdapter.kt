package com.mdroid.countriesinfo.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mdroid.countriesinfo.databinding.AdapterCountryRowBinding
import com.mdroid.countriesinfo.model.CountriesModel
import com.mdroid.countriesinfo.utils.ValidationUtil

/**
 * An Adapter class for showing the list of countries in a card view
 * @author MayuR
 */
class CountriesAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<PlacesViewHolder>() {

    var countriesList = mutableListOf<CountriesModel>()
    lateinit var onItemClickListener: OnItemClickListener

    fun setCountries(countries: List<CountriesModel>) {
        this.countriesList = countries.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCountryRowBinding.inflate(inflater, parent, false)
        return PlacesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {

        val countyr = countriesList[position]
        if (ValidationUtil.validateACountry(countyr)) {
            holder.binding.txtCountryName.text = countyr.name.common
            holder.binding.txtSubName.text = countyr.region
            Glide.with(holder.itemView.context).load(Uri.parse(countyr.flags.png)).into(holder.binding.imgCountry)
            //holder.binding.imgCountry.load(countyr.flags.svg)
        }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(countyr)
        }
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }
}

class PlacesViewHolder(val binding: AdapterCountryRowBinding) : RecyclerView.ViewHolder(binding.root) {

}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class OnClickListener(val clickListener: (model: CountriesModel) -> Unit) {
    fun onClick(model: CountriesModel) = clickListener(model)
}