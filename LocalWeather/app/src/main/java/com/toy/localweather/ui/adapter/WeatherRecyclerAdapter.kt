package com.toy.localweather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.toy.localweather.databinding.ItemWeatherBinding
import com.toy.localweather.databinding.ItemWeatherDetailBinding
import com.toy.localweather.databinding.ItemWeatherHeaderBinding
import com.toy.localweather.repository.model.LocalWeatherRecyclerModel
import com.toy.localweather.repository.model.LocationWeatherModel

class WeatherRecyclerAdapter(private var items: List<LocalWeatherRecyclerModel>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val binding = ItemWeatherHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return HeaderViewHolder(binding)
        } else {
            val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder)
            holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    fun updateData(updatedItems: List<LocalWeatherRecyclerModel>) {
        items = updatedItems
    }

    class ViewHolder(private var binding: ItemWeatherBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocalWeatherRecyclerModel) {

            binding.tvLocal.text = item.local
            bindItem(binding.includeToday, item.today)
            bindItem(binding.includeTomorrow, item.tomorrow)
        }

        private fun bindItem(binding: ItemWeatherDetailBinding, item: LocationWeatherModel) {
            binding.tvWeather.text = item.weatherStateName
            binding.tvTemp.text = String.format("%.0f℃", item.theTemp)
            binding.tvHumi.text = String.format("%s％", item.humidity)
            Glide.with(binding.ivWeather).load(item.weatherStateUrl)
                    .into(binding.ivWeather)
        }
    }

    class HeaderViewHolder(binding: ItemWeatherHeaderBinding) : RecyclerView.ViewHolder(binding.root)
}