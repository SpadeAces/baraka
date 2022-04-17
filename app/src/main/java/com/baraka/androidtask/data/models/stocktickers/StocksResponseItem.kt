package com.baraka.androidtask.data.models.stocktickers


import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "stocks")
data class StocksResponseItem(
    var StockItem: List<Stocks>
)