package com.baraka.androidtask.data.remote.reporitory

import com.baraka.androidtask.data.local.db.AppDao
import com.baraka.androidtask.data.remote.newsfeed.NewsFeedApiService
import com.baraka.androidtask.data.remote.stocks.ApiService
import javax.inject.Inject

class NewsFeedRepository @Inject constructor(
    private val newsFeedApiService: NewsFeedApiService,
    localDataSource: AppDao
) {

    suspend fun getPosts() = newsFeedApiService.getPosts()

}