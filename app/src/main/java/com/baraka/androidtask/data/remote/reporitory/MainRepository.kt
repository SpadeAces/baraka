package com.baraka.androidtask.data.remote.reporitory

import com.baraka.androidtask.data.local.db.AppDao
import com.baraka.androidtask.data.remote.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {

    suspend fun getPosts() = apiService.getPosts()

}