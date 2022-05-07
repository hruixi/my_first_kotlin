package com.rain.mykotlin.view.customize.bli3D

import android.view.View
import androidx.viewpager2.widget.ViewPager2

/**
 * 作者: hruix
 * 时间: 2022/2/24
 * 描述: XXX
 */
class BliPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val bliConstraintLayout = page as BliLayout
        /**
         * 倾斜度
         */
        val tiltDegree = 34
        val v = position * tiltDegree
        bliConstraintLayout.rotateY = v
        bliConstraintLayout.isLeftRotate = position > 0
    }
}