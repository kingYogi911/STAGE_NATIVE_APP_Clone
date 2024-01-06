package com.yogi.stagenativeapp.moduels.home.repositories

import com.yogi.stagenativeapp.data.DataState
import com.yogi.stagenativeapp.moduels.home.data.ArtistListItem
import com.yogi.stagenativeapp.moduels.home.data.ArtistMovieCardItem
import com.yogi.stagenativeapp.moduels.home.data.HomeScreenData
import com.yogi.stagenativeapp.moduels.home.data.HomeScrollItem
import com.yogi.stagenativeapp.moduels.movieAdapters.MovieItemViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository_Impl @Inject constructor() : HomeRepository {
    override suspend fun getHomeData(): Flow<DataState<HomeScreenData>> = flow {
        emit(DataState.Loading)
        withContext(Dispatchers.IO) {
            val movie = MovieItemViewHolder.MovieItem(
                0,
                "",
                true
            )
            val list1 = (1..6).map {
                HomeScrollItem(
                    it,
                    "",
                    true
                )
            }
            val list2 = (1..6).map {
                MovieItemViewHolder.MovieItem(
                    it,
                    "",
                    true
                )
            }
            val artist = (1..6).map {
                ArtistListItem(
                    it,
                    "",
                    "Artist $it"
                )
            }
            val artistMovies = artist.map {
                ArtistMovieCardItem(
                    id = it.id,
                    title = "${it.name} Shows",
                    movies = list2
                )
            }
            HomeScreenData(
                autoScrollData = list1,
                promotionalImage = "",
                to20List = list2,
                advertisementVideo1 = movie,
                vipShows = list2,
                advertisementVideo2 = movie,
                vipVideos = list2,
                comingSoonList = list2,
                specialForYouList = list2,
                advertisementVideo3 = movie,
                webSeriesList = list2,
                videosList = list2,
                artistsList = artist,
                artistMoviesCards = artistMovies
            )
        }.let {
            emit(DataState.Success(it))
        }
    }.catch {
        it.printStackTrace()
        emit(DataState.Error(it))
    }

}