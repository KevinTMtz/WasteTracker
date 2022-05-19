package com.bruwus.wastetracker.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.databinding.HomeRowBinding
import com.bruwus.wastetracker.ui.home.data.entities.NewsArticle
import com.bumptech.glide.Glide

class HomeViewHolder(view: View, private val clickListener: HomeAdapter.ClickListener?): RecyclerView.ViewHolder(view), View.OnClickListener {
    private val binding = HomeRowBinding.bind(itemView)

    fun bind(article: NewsArticle) {
        binding.homeRowTitle.text = article.title
        binding.homeRowSource.text = article.source.name
        if (article.urlToImage.isNotEmpty()) {
            Glide.with(binding.root)
                .load(article.urlToImage).centerCrop()
                .into(binding.homeRowImage)
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
