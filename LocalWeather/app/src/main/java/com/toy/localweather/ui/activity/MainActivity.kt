package com.toy.localweather.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.toy.localweather.LocalWeatherApp
import com.toy.localweather.R
import com.toy.localweather.databinding.ActivityMainBinding
import com.toy.localweather.di.component.MainComponent
import com.toy.localweather.repository.model.LocalWeatherRecyclerModel
import com.toy.localweather.ui.adapter.WeatherRecyclerAdapter
import com.toy.localweather.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent
    private lateinit var viewModel: MainViewModel

    @Inject
    protected lateinit var mViewModelProvider: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDI()
        setViewModel()
        setDatabinding()
        setObserver()
        setEvent()
    }

    private fun setObserver() {
        viewModel.weatherList.observe(this, { list ->
            if (binding.rvMain.adapter == null || list.size == 0) {
                initAdapter(list)
            } else {
                binding.rvMain.adapter?.let {
                    (it as WeatherRecyclerAdapter).updateData(list)
                    it.notifyDataSetChanged()
                    binding.slMain.isRefreshing = false
                }
            }
        })
    }

    private fun initAdapter(list: List<LocalWeatherRecyclerModel>) {
        binding.rvMain.adapter = WeatherRecyclerAdapter(list)
    }

    private fun setEvent() {
        binding.slMain.setOnRefreshListener {
            viewModel.getWeatherData()
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, mViewModelProvider).get(MainViewModel::class.java)
    }

    private fun setDatabinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }

    private fun setDI() {
        mainComponent =
                (applicationContext as LocalWeatherApp).appComponent.mainComponent().create()
        mainComponent.inject(this)
    }
}