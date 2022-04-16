package com.baraka.androidtask.data.remote


import com.baraka.androidtask.data.models.PostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<PostsResponse>
}