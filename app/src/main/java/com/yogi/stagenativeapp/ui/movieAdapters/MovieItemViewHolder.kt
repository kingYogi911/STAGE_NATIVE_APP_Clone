package com.yogi.stagenativeapp.ui.movieAdapters

import androidx.recyclerview.widget.RecyclerView
import com.yogi.stagenativeapp.databinding.MovieCardItemLayoutBinding

class MovieItemViewHolder(
    private val binding: MovieCardItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieItem) {

    }

    data class MovieItem(val id: Int, val img: String, val isVIP: Boolean)
}