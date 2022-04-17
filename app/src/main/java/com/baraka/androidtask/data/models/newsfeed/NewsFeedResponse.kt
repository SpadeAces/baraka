package com.baraka.androidtask.data.models.newsfeed

data class NewsFeedResponse(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
)