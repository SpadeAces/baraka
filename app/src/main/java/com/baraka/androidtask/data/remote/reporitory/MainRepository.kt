package com.baraka.androidtask.data.remote.reporitory

import android.util.Log
import com.baraka.androidtask.constants.AppConstants
import com.baraka.androidtask.data.local.db.AppDao
import com.baraka.androidtask.data.models.stocktickers.Stocks
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
    private val stocksList: ArrayList<Stocks> = ArrayList()



    suspend fun getStocks() : List<Stocks>{
        val url = URL(AppConstants.ApiConfiguration.STOCK_TICKERS)
        val reader = BufferedReader(InputStreamReader(url.openStream()))

        var info = reader.readLine()
        var i = 0
        var isActualData = false
        while (info != null){
            val line  = info.split(",")

            Log.d("line",line.toString())
            val stock = stocksList.findLast{ it.title == line[0] }
            if(isActualData)
            stocksList.add(
                Stocks(line[0],line[1].toDouble()
                    , stock?.previousValue ?: line[1].toDouble()
                    , line[1].toDouble()
            ))
            info = reader.readLine()
            isActualData = true
            i++

        }

        return stocksList

    }

    suspend fun getNewsFeed() = apiService.getNewsFeed()

}