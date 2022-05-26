package com.bruwus.wastetracker.ui.identify.data

import com.bruwus.wastetracker.ui.identify.data.entities.IdentifyWasteResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class IdentifyWaste {
    private val apiBase = "https://floating-springs-09273.herokuapp.com/"
    private val api: IdentifyApiService = Retrofit.Builder().baseUrl(apiBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IdentifyApiService::class.java)

    fun identifyWasteByImage(imageUrl: String): Flow<IdentifyWasteResult> {
        return flow {
            api.getWasteIdentification(
                Locale.getDefault().language,
                imageUrl
            ).apply {
                emit(this)
            }
        }
    }
}