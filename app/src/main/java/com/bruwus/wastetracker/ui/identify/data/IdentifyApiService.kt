package com.bruwus.wastetracker.ui.identify.data

import com.bruwus.wastetracker.ui.identify.data.entities.IdentifyWasteResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IdentifyApiService {
    @GET("identify")
    suspend fun getWasteIdentification(
        @Query("image") image: String,
    ): IdentifyWasteResult
}