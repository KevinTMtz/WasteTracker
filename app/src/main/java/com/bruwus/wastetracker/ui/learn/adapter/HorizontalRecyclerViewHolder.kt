package com.bruwus.wastetracker.ui.learn.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.databinding.CellColumnBinding
import com.bruwus.wastetracker.ui.learn.data.RecyclerViewData
import com.bumptech.glide.Glide

class HorizontalRecyclerViewHolder(view: View, private val clickListener: HorizontalRecyclerViewAdapter.ClickListener?): RecyclerView.ViewHolder(view), View.OnClickListener {
    private val binding = CellColumnBinding.bind(itemView)

    fun bind(recyclerViewData: RecyclerViewData) {
        binding.cellColumnTitle.text = recyclerViewData.title
        binding.cellColumnDescription.text = recyclerViewData.description
        Glide.with(binding.root)
            .load(recyclerViewData.imageUrl).centerCrop()
            .into(binding.cellColumnImage)
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