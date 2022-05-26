package com.bruwus.wastetracker.ui.news.data

import com.bruwus.wastetracker.BuildConfig
import com.bruwus.wastetracker.ui.news.data.entities.NewsArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class NewsRepository {

    private val apiKey = BuildConfig.NEWS_API_KEY
    private val apiBase = "https://newsapi.org/v2/"
    private val api: NewsApiService = Retrofit.Builder().baseUrl(apiBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApiService::class.java)

    fun getRecentClimateNews(): Flow<List<NewsArticle>> {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val period = Period.ofDays(15)
        val endDate = LocalDate.now()
        val startDate = endDate.minus(period)
        return flow {
            api.getNews(
                "climate",
                dateFormat.format(startDate),
                dateFormat.format(endDate),
                apiKey
            ).articles.apply {
                emit(this)
            }
        }
    }

}
