package com.yogi.stagenativeapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.yogi.stagenativeapp.R
import com.yogi.stagenativeapp.databinding.HomeViewPagerItemBinding
import com.yogi.stagenativeapp.ui.home.data.HomeScrollItem

class InfiniteRecyclerAdapter(
) : RecyclerView.Adapter<InfiniteRecyclerAdapter.InfiniteRecyclerViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(originalList: List<HomeScrollItem>){
        if (originalList.isNotEmpty()) {
            newList = listOf(originalList.last()) + originalList + listOf(originalList.first())
        }else{
            newList = emptyList()
        }
        notifyDataSetChanged()
    }
    private var newList: List<HomeScrollItem> = emptyList()


    class InfiniteRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = HomeViewPagerItemBinding.bind(itemView)

        fun bind(sample: HomeScrollItem) {
            binding.apply {
                tv.text = "${sample.id}"
                btVip.isVisible = sample.isVIP
            }
        }

        companion object {
            fun from(parent: ViewGroup) : InfiniteRecyclerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(
                    R.layout.home_view_pager_item,
                    parent, false)
                return InfiniteRecyclerViewHolder(itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteRecyclerViewHolder {
        return InfiniteRecyclerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: InfiniteRecyclerViewHolder, position: Int) {
        holder.bind(newList[position])
    }

    override fun getItemCount(): Int {
        return newList.size
    }

}