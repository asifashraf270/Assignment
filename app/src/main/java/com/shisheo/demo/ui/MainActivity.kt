package com.shisheo.demo.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shisheo.demo.R
import com.shisheo.demo.databinding.ActivityMainBinding
import com.shisheo.demo.utils.AppLogger
import com.shisheo.demo.utils.Status
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

private const val TAG = "MainActivity"

class MainActivity : DaggerAppCompatActivity(), View.OnClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RestaurantViewModel by viewModels() {
        viewModelFactory
    }
    lateinit var adapter: RestaurantRecyclerView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = RestaurantRecyclerView(this)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        adapter.onClickListener = this
        viewModel.loadRestaurantList.value = ""
        viewModel.restaurantListResponse.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    it.data?.let { it1 -> adapter.updateList(it1.toList()) }
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    AppLogger.errorMessage(TAG, it.toString())
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rootView -> {
                var position = v.getTag() as Int
                if (adapter.restaurantList.get(position).ratingStar == 0f)
                    adapter.restaurantList.get(position).ratingStar = 5f
                else
                    adapter.restaurantList.get(position).ratingStar=0f
                adapter.notifyItemChanged(position)
            }
        }
    }
}