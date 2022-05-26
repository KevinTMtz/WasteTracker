package com.bruwus.wastetracker.ui.news.data.entities

import com.google.gson.annotations.SerializedName

data class NewsSource(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String
)
