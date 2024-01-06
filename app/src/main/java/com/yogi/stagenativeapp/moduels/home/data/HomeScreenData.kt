package com.yogi.stagenativeapp.moduels.home.data

import com.yogi.stagenativeapp.moduels.movieAdapters.MovieItemViewHolder

data class HomeScreenData(
    val autoScrollData:List<HomeScrollItem>,
    val promotionalImage:String,
    val to20List:List<MovieItemViewHolder.MovieItem>,
    val advertisementVideo1:MovieItemViewHolder.MovieItem,
    val vipShows:List<MovieItemViewHolder.MovieItem>,
    val advertisementVideo2:MovieItemViewHolder.MovieItem,
    val vipVideos:List<MovieItemViewHolder.MovieItem>,
    val comingSoonList:List<MovieItemViewHolder.MovieItem>,
    val specialForYouList:List<MovieItemViewHolder.MovieItem>,
    val advertisementVideo3:MovieItemViewHolder.MovieItem,
    val webSeriesList:List<MovieItemViewHolder.MovieItem>,
    val videosList:List<MovieItemViewHolder.MovieItem>,
    val artistsList:List<ArtistListItem>,
    val artistMoviesCards:List<ArtistMovieCardItem>
)