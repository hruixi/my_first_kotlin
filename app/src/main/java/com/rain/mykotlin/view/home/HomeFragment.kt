package com.rain.mykotlin.view.home

import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Px
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.rain.mykotlin.R
import com.rain.mykotlin.databinding.FragmentHomeBinding
import com.rain.mykotlin.view.customize.autoLoop.BannerAdapter
import com.rain.mykotlin.view.customize.autoLoop.BaseBannerAdapter
import com.rain.mykotlin.view.customize.autoLoop.GalleryTransformer
import com.rain.mykotlin.view.customize.bli3D.BliPageTransformer
import com.rain.mykotlin.view.customize.bli3D.BliPagerAdapter
import com.rain.mykotlin.view.customize.bli3D.BliViewItemBean
import com.rain.mykotlin.view.customize.bli3D.HomeViewHolder

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var pagerAdapter: BliPagerAdapter<BliViewItemBean>
    private lateinit var bannerAdapter: BannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        val beanList = mutableListOf<BliViewItemBean>()
        beanList.add(BliViewItemBean(resources.getDrawable(R.mipmap.image_1, null), "222", "333", "444"))
        beanList.add(BliViewItemBean(resources.getDrawable(R.mipmap.image_2, null), "31", "245", "467"))
        beanList.add(BliViewItemBean(resources.getDrawable(R.mipmap.image_3, null), "435", "335633", "1002"))

//        pagerAdapter = object : BliPagerAdapter<BliViewItemBean>(context, R.layout.bli_viewpager_item, beanList) {
//            override fun toBindVH(holder: HomeViewHolder, position: Int) {
//                holder.binding.bliImg.setImageDrawable((getItemData(position))?.image)
//                holder.binding.bliLike.text = getItemData(position)?.likeCount
//                holder.binding.bliShare.text = getItemData(position)?.sharedCount
//                holder.binding.bliComment.text = getItemData(position)?.commentCount
//            }
//        }.apply {
//            setIsCanLoop(true)
//            binding.bliVp.adapter = this
//        }
//        binding.bliVp.setPageTransformer(bilPageTransformer)

        /** 循环或者自动 **/
        bannerAdapter = BannerAdapter()
        binding.bliBannerVp
            //设置生命周期
            .setLifecycleRegistry(lifecycle)
            //开启自动轮询
            .setAutoPlay(false)
            //开启循环滚动
            .setCanLoop(true)
            //设置轮询间隔
            .setInterval(2)
            //设置显示多个视图的宽度
            .setRevealWidth(50)
            //设置视图间隔
            .setPageMargin(8)
            //设置滚动动画
            .addPageTransformer(BliPageTransformer())
            .addPageTransformer(GalleryTransformer())
            //显示指示器
            .setCanShowIndicator(true)
            //设置适配器
            .setAdapter(bannerAdapter)
            .create(beanList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
