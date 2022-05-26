package com.bruwus.wastetracker.ui.news.data

import com.bruwus.wastetracker.ui.news.data.entities.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NewsApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("from") startingDate: String,
        @Query("to") endingDate: String,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("pageSize") pageSize: Int = 20,
    ): NewsResponse

}