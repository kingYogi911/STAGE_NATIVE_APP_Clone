package com.yogi.stagenativeapp.di

import com.yogi.stagenativeapp.moduels.home.repositories.HomeRepository
import com.yogi.stagenativeapp.moduels.home.repositories.HomeRepository_Impl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHomeRepository(homeRepositoryImpl: HomeRepository_Impl):HomeRepository

}