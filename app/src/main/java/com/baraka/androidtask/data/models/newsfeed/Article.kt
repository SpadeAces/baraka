package com.baraka.androidtask.data.models.newsfeed

import androidx.room.Entity

@Entity(tableName = "articles")
data class Article(
    var author: String,
    var content: String,
    var description: String,
    var publishedAt: String,
    var source: Source,
    var title: String,
    var url: String,
    var urlToImage: String
)