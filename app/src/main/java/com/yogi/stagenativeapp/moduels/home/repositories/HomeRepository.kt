package com.yogi.stagenativeapp.moduels.home.repositories

import com.yogi.stagenativeapp.data.DataState
import com.yogi.stagenativeapp.moduels.home.data.HomeScreenData
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getHomeData(): Flow<DataState<HomeScreenData>>
}