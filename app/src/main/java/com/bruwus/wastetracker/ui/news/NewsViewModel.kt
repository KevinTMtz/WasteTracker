package com.bruwus.wastetracker.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruwus.wastetracker.ui.news.data.NewsRepository
import com.bruwus.wastetracker.ui.news.data.entities.NewsArticle
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val newsRepository =  NewsRepository()
    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> get() = _articles

    fun loadArticles() {
        viewModelScope.launch {
            newsRepository.getRecentClimateNews()
                .catch {  exception ->
                    Log.v("NewsApiException", exception.message!!)
                    _articles.postValue(emptyList())
                }
                .collect { climateArticles ->
                    _articles.postValue(climateArticles)
                }
        }
    }

}