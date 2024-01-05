package com.yogi.stagenativeapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.stagenativeapp.ui.home.data.HomeScrollItem
import com.yogi.stagenativeapp.ui.movieAdapters.MovieItemViewHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _data = MutableStateFlow<List<HomeScrollItem>>(emptyList())
    val data get() = _data.asStateFlow()

    private val _vipMovies = MutableStateFlow<List<MovieItemViewHolder.MovieItem>>(emptyList())
    val vipMovies get() = _vipMovies.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _data.value = (1..6).map {
                delay(100)
                HomeScrollItem(
                    it,
                    "",
                    it % 2 == 0
                )
            }
            _vipMovies.value = (1..6).map {
                delay(100)
                MovieItemViewHolder.MovieItem(
                    it,
                    "",
                    it % 2 == 0
                )
            }
        }
    }
}