package com.baraka.androidtask.utils

import com.baraka.androidtask.data.models.newsfeed.Article
import java.math.RoundingMode
import java.text.DecimalFormat


/**
 * Created by manzo on 17,April,2022
 */

fun roundOfStocksValue(stockValue: Double): Double {
    return if (stockValue.equals(0.00)) {
        stockValue
    } else {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        df.format(stockValue).toDouble()
    }
}
