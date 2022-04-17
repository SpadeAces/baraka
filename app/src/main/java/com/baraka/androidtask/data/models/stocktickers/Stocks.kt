package com.baraka.androidtask.data.models.stocktickers

data class Stocks(
    var title : String,
    var value : Double,

    var previousValue : Double = 0.00,
    var currentValue : Double = 0.00,
    var isUp : Boolean = previousValue <= currentValue
)
