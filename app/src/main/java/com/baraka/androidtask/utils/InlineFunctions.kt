package com.baraka.androidtask.utils

import com.baraka.androidtask.data.models.newsfeed.Article
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


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

fun String.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}
