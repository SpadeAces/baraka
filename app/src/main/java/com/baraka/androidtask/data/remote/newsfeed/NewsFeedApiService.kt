package com.baraka.androidtask.data.remote.newsfeed


import com.baraka.androidtask.data.models.PostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsFeedApiService {

    @GET("posts")
    suspend fun getPosts(): Response<PostsResponse>
}