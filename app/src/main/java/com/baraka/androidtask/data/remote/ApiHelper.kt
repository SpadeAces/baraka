package com.baraka.androidtask.data.remote

import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUsers() = apiService.getPosts()
}