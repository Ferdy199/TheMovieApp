package com.ferdsapp.home.di

import com.ferdsapp.home.data.paging.RemotePagingDataSource

fun interface RemotePagingFactory {
    fun create(): RemotePagingDataSource
}