package com.yogi.stagenativeapp.moduels.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UpcomingViewModel : ViewModel() {

    private val _data = MutableStateFlow<List<UpcomingItemData>>(emptyList())
    val data get() = _data.asStateFlow()

    init {

        viewModelScope.launch {
            delay(2000)
            _data.value = listOf(
                UpcomingItemData(
                    "",
                    "Chhapa",
                    "Chirag Bhasin"
                ),
                UpcomingItemData(
                    "",
                    "Akhada Phir se",
                    "Gaggler Films"
                ),
                UpcomingItemData(
                    "",
                    "HR-13",
                    "Sushant"
                )
            ).let {
                it + it + it
            }
        }
    }
}