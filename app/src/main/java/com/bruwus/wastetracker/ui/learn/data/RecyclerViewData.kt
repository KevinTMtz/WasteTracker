package com.bruwus.wastetracker.ui.learn.data

import java.io.Serializable

open class RecyclerViewData (
    val id: String? = "",
    val title: String? = "",
    val description: String? = "",
    val imageUrl: String? = ""
): Serializable