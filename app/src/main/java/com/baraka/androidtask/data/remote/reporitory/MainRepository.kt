package com.baraka.androidtask.data.remote.reporitory

import android.util.Log
import com.baraka.androidtask.constants.AppConstants
import com.baraka.androidtask.data.local.db.AppDao
import com.baraka.androidtask.data.models.stocktickers.StocksResponseItem
import com.baraka.androidtask.data.remote.ApiService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {
    private val stocksList: ArrayList<StocksResponseItem> = ArrayList()


    suspend fun getStocks() : List<StocksResponseItem>{
        val url = URL(AppConstants.ApiConfiguration.STOCK_TICKERS)
        val reader = BufferedReader(InputStreamReader(url.openStream()))

        var info = reader.readLine()
        var isActualData = false
        while (info != null){
            val line  = info.split(",")

            Log.d("line",line.toString())

            if(isActualData)
            stocksList.add(StocksResponseItem(line))
            info = reader.readLine()
            isActualData = true

        }

        return stocksList

    }

    suspend fun getNewsFeed() = apiService.getNewsFeed()

}