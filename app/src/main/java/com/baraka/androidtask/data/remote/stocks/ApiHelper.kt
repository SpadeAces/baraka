package com.baraka.androidtask.data.remote.stocks

import com.baraka.androidtask.data.remote.stocks.ApiService
import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUsers() = apiService.getStocks()
}