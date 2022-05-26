package com.bruwus.wastetracker.ui.identify.data.entities

import com.google.gson.annotations.SerializedName

data class IdentifyWasteResult(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("howto") val howTo: String,
    @SerializedName("certainty") val certainty: Double
)
