package com.bruwus.wastetracker.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.ui.home.data.entities.NewsArticle

class HomeAdapter (private var articles: List<NewsArticle>): RecyclerView.Adapter<HomeViewHolder>() {

    interface ClickListener{
        fun onItemClick(view: View, pos: Int)
    }

    private var clickListener: ClickListener? = null

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_row, parent, false)

        return HomeViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
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