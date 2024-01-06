package com.yogi.stagenativeapp.moduels.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogi.stagenativeapp.R
import com.yogi.stagenativeapp.databinding.HorizontalListWithSeeAllAndTitleBinding
import com.yogi.stagenativeapp.moduels.home.data.ArtistMovieCardItem
import com.yogi.stagenativeapp.moduels.movieAdapters.MovieAdapter

class ArtistMovieCardAdapter : RecyclerView.Adapter<ArtistMovieCardAdapter.ViewHolder>() {

    private val data = mutableListOf<ArtistMovieCardItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data:List<ArtistMovieCardItem>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = HorizontalListWithSeeAllAndTitleBinding.bind(itemView)
        val adapter = MovieAdapter()
        init {
            binding.rv.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.horizontal_list_with_see_all_and_title,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.apply{
            tvTitle.text = item.title
            holder.adapter.setData(item.movies)
        }
    }
}