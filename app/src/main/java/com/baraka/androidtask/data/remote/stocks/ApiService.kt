package com.baraka.androidtask.data.remote.stocks


import com.baraka.androidtask.data.models.stocktickers.StocksResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("stocks.csv")
    suspend fun getStocks(): Response<StocksResponse>
}