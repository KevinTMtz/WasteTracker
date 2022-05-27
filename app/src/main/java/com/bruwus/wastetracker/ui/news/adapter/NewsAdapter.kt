package com.bruwus.wastetracker.ui.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.ui.news.data.entities.NewsArticle

class NewsAdapter (private var articles: List<NewsArticle>): RecyclerView.Adapter<NewsViewHolder>() {

    interface ClickListener{
        fun onItemClick(view: View, pos: Int)
    }

    private var clickListener: ClickListener? = null

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)

        return NewsViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    fun setArticles(articles: List<NewsArticle>) {
        this.articles = articles
    }

    override fun getItemCount() = articles.size

    fun getArticle(pos: Int): NewsArticle {
        return articles[pos]
    }
}