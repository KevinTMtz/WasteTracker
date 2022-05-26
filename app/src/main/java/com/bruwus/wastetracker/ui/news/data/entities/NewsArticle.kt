package com.bruwus.wastetracker.ui.news.data.entities

import com.google.gson.annotations.SerializedName

data class NewsArticle(
    @SerializedName("source") var source: NewsSource,
    @SerializedName("author") var author: String,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String,
    @SerializedName("urlToImage") var urlToImage: String?,
)
