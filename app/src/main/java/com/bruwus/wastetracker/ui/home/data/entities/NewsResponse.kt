package com.bruwus.wastetracker.ui.home.data.entities

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("totalResults") var totalResults: Int,
    @SerializedName("articles") var articles: List<NewsArticle>
)
