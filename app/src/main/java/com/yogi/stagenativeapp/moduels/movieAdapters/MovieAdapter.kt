package com.yogi.stagenativeapp.moduels.movieAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogi.stagenativeapp.databinding.MovieCardItemLayoutBinding

class MovieAdapter : RecyclerView.Adapter<MovieItemViewHolder>() {

    private var data:List<MovieItemViewHolder.MovieItem> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data:List<MovieItemViewHolder.MovieItem>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            MovieCardItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(data[position])
    }
}