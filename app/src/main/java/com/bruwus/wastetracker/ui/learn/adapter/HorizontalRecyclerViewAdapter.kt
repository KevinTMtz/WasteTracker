package com.bruwus.wastetracker.ui.learn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.ui.learn.data.RecyclerViewData

class HorizontalRecyclerViewAdapter (private var recyclerList: List<RecyclerViewData>): RecyclerView.Adapter<HorizontalRecyclerViewHolder>() {
    private var clickListener: ClickListener? = null

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener{
        fun onItemClick(view: View, pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_column, parent, false)

        return HorizontalRecyclerViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: HorizontalRecyclerViewHolder, position: Int) {
        val recyclerViewData = recyclerList[position]

        holder.bind(recyclerViewData)
    }

    fun setData(articles: List<RecyclerViewData>) {
        this.recyclerList = articles
    }

    override fun getItemCount() = recyclerList.size

    fun getData(pos: Int): RecyclerViewData {
        return recyclerList[pos]
    }
}