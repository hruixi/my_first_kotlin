package com.huahua.mykotlin.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.huahua.mykotlin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView()
        return root
    }

    private fun initView() {
        val pagerAdapter = object : RecyclerView.Adapter<HomeViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
                TODO("Not yet implemented")
            }

            override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
                TODO("Not yet implemented")
            }

            override fun getItemCount(): Int {
                TODO("Not yet implemented")
            }
        }

        val pageTransformer = ViewPager2.PageTransformer { page, position ->
            
        }

        binding.bliVp.adapter = pagerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
