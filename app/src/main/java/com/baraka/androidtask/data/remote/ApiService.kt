package com.baraka.androidtask.data.remote


import com.baraka.androidtask.data.models.newsfeed.NewsFeedResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cnn.json")
    suspend fun getNewsFeed(): Response<NewsFeedResponse>
}