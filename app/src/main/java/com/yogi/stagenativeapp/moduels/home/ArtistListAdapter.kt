package com.yogi.stagenativeapp.moduels.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogi.stagenativeapp.R
import com.yogi.stagenativeapp.databinding.ArtistCardListItemBinding
import com.yogi.stagenativeapp.moduels.home.data.ArtistListItem

class ArtistListAdapter : ListAdapter<ArtistListItem,ArtistListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(diffUtil).build()
) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ArtistCardListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.artist_card_list_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.binding.apply {

        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ArtistListItem>() {
            override fun areItemsTheSame(
                oldItem: ArtistListItem,
                newItem: ArtistListItem
            ): Boolean {
                return oldItem == newItem || oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ArtistListItem,
                newItem: ArtistListItem
            ): Boolean {
                return oldItem.id == newItem.id && oldItem.image == newItem.image && oldItem.name == newItem.name
            }
        }
    }
}