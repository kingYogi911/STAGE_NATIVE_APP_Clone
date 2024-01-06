package com.yogi.stagenativeapp.moduels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.stagenativeapp.data.DataState
import com.yogi.stagenativeapp.moduels.home.data.HomeScreenData
import com.yogi.stagenativeapp.moduels.home.repositories.HomeRepository
import com.yogi.stagenativeapp.moduels.movieAdapters.MovieItemViewHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository:HomeRepository
) : ViewModel() {

    private val _data = MutableStateFlow<DataState<HomeScreenData>>(DataState.Loading)
    val data get() = _data.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            repository.getHomeData().collectLatest {
                _data.value = it
            }
        }
    }
}