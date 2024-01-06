package com.yogi.stagenativeapp.data

import java.lang.Exception

sealed class DataState<out T>{
    data object Loading:DataState<Nothing>()
    data class Success<R>(val data:R):DataState<R>()
    data class Error(val error:Throwable):DataState<Nothing>()
}