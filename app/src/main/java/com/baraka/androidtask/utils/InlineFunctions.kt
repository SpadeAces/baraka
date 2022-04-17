package com.baraka.androidtask.utils

import com.baraka.androidtask.data.models.newsfeed.Article
import java.math.RoundingMode
import java.text.DecimalFormat


/**
 * Created by manzo on 17,April,2022
 */

fun roundOfStocksValue(stockValue: String?): String {
    return if (stockValue.isNullOrEmpty()) {
        "0.00"
    } else {
        val tempValue: Double = (stockValue.toDouble())
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        df.format(tempValue).toString()
    }
}
