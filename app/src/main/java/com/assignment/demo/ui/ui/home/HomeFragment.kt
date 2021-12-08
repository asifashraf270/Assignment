package com.assignment.demo.ui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.demo.R
import com.assignment.demo.databinding.FragmentHomeBinding
import com.assignment.demo.ui.HomeViewModel
import com.assignment.demo.ui.MostViewRecyclerViewAdapter
import com.assignment.demo.utils.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var homeViewModel: com.assignment.demo.ui.HomeViewModel
    lateinit var adapter:MostViewRecyclerViewAdapter
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(HomeViewModel::class.java)
        adapter= activity?.let { MostViewRecyclerViewAdapter(it) }!!
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        registerObserver()
        return binding.root
    }

    fun registerObserver() {
        homeViewModel.section="all-sections"
        homeViewModel.period=7
        homeViewModel.key="N0KrgSkqRinVsSzC77366szoVblWsLKF"
        homeViewModel.loadList.value=""
        homeViewModel.mostViewedMost.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                    adapter.updateList(it.data?.results)
                }
                Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(context, it.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}