package com.baraka.androidtask.constants

import androidx.annotation.StringDef


object AppConstants {

    @StringDef(ApiConfiguration.NEWS_FEED, ApiConfiguration.STOCK_TICKERS)
    annotation class ApiConfiguration {
        companion object {
            const val NEWS_FEED = "https://saurav.tech/NewsAPI/everything/"
            const val STOCK_TICKERS = "https://raw.githubusercontent.com/dsancov/TestData/main/stocks.csv"
        }
    }

    @StringDef(DbConfiguration.DB_NAME)
    annotation class DbConfiguration {
        companion object {
            const val DB_NAME = "BaseProject"
        }
    }


    @StringDef(DataStore.DATA_STORE_NAME,DataStore.LOCALIZATION_KEY_NAME,DataStore.USER_NAME_KEY)
    annotation class DataStore {
        companion object {
            const val DATA_STORE_NAME = "BaseProject"
            const val LOCALIZATION_KEY_NAME = "lang"
            const val USER_NAME_KEY = "user_name_key"
        }
    }

}