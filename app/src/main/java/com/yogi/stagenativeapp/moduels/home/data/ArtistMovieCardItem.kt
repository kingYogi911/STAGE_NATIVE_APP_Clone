package com.yogi.stagenativeapp.moduels.home.data

import com.yogi.stagenativeapp.moduels.movieAdapters.MovieItemViewHolder

data class ArtistMovieCardItem (
    val id:Int,
    val title:String,
    val movies:List<MovieItemViewHolder.MovieItem>
)