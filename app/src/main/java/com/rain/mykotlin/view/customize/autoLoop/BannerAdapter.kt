package com.rain.mykotlin.view.customize.autoLoop

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rain.mykotlin.R
import com.rain.mykotlin.databinding.BliViewpagerItemBinding
import com.rain.mykotlin.view.customize.bli3D.BliViewItemBean

class BannerAdapter : BaseBannerAdapter<BliViewItemBean, BannerAdapter.ViewHolder>() {
    override fun getLayoutId(viewType: Int) = R.layout.bli_viewpager_item

    override fun onBind(holder: ViewHolder, data: BliViewItemBean, position: Int, pageSize: Int) {
        holder.binding.bliImg.setImageDrawable(data.image)
        holder.binding.bliLike.text = data.likeCount
        holder.binding.bliShare.text = data.sharedCount
        holder.binding.bliComment.text = data.commentCount
    }

    override fun createViewHolder(parent: ViewGroup, itemView: View, viewType: Int): ViewHolder {
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: BliViewpagerItemBinding = BliViewpagerItemBinding.bind(itemView)
    }
}
