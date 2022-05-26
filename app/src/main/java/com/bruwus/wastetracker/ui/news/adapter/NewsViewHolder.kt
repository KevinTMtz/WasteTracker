package com.bruwus.wastetracker.ui.news.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.NewsRowBinding
import com.bruwus.wastetracker.ui.news.data.entities.NewsArticle
import com.bumptech.glide.Glide

class NewsViewHolder(view: View, private val clickListener: NewsAdapter.ClickListener?): RecyclerView.ViewHolder(view), View.OnClickListener {
    private val binding = NewsRowBinding.bind(itemView)

    fun bind(article: NewsArticle) {
        binding.homeRowTitle.text = article.title
        binding.homeRowSource.text = article.source.name

        if (!article.urlToImage.isNullOrEmpty()) {
            Glide.with(binding.root)
                .load(article.urlToImage).centerCrop()
                .into(binding.homeRowImage)
        } else {
            binding.homeRowImage.setImageDrawable(
                AppCompatResources.getDrawable(itemView.context, R.drawable.ic_launcher_background)
            )
        }
    }

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if(view != null) {
            clickListener?.onItemClick(view, adapterPosition)
        }
    }
}
