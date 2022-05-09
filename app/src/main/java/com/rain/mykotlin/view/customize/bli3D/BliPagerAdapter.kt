package com.rain.mykotlin.view.customize.bli3D

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rain.mykotlin.databinding.BliViewpagerItemBinding

/**
 * 作者: hruix
 * 时间: 2022/2/24
 * 描述: XXX
 */
abstract class BliPagerAdapter<T>(context: Context?, layoutId: Int, mutableList: MutableList<T>) : RecyclerView.Adapter<HomeViewHolder>() {
    private var data = mutableListOf<T>()
    private var layoutId = -1
    private var isCanLoop = false
    private lateinit var context: Context

    init {
        this.data = mutableList
        this.layoutId = layoutId
        if (context != null) {
            this.context = context
        }
    }

    fun updateData(newData: MutableList<T>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    protected fun getItemData(position: Int): T? {
        return data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val realPosition: Int = getRealPosition(position)
        toBindVH(holder, realPosition)
    }

    override fun getItemCount(): Int {
        return if (isCanLoop && data.size > 1) {
            Int.MAX_VALUE /** 循坏滑动的精髓在这 **/
        } else {
            data.size
        }
    }

    fun setIsCanLoop(canLoop: Boolean) {
        this.isCanLoop = canLoop
    }

    private fun getRealPosition(position: Int): Int {
        val pageSize = data.size
        if (pageSize == 0) {
            return 0
        }
        return if (isCanLoop) (position + pageSize) % pageSize else position
    }

    abstract fun toBindVH(holder: HomeViewHolder, position: Int)
}

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding: BliViewpagerItemBinding = BliViewpagerItemBinding.bind(itemView)
}