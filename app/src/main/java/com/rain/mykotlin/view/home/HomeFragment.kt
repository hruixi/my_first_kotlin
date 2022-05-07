package com.rain.mykotlin.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rain.mykotlin.databinding.FragmentHomeBinding
import com.rain.mykotlin.view.customize.bli3D.BliPageTransformer
import com.rain.mykotlin.view.customize.bli3D.BliPagerAdapter

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val bilPageTransformer = BliPageTransformer()
    private val pagerAdapter = BliPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.bliVp.adapter = pagerAdapter
        binding.bliVp.setPageTransformer(bilPageTransformer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
