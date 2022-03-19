package com.moving.germany.translate

import com.moving.germany.models.TranslationResponse
import retrofit2.http.GET

interface TranslateInterface {

    @GET("summary")
    suspend fun getSummary(): TranslationResponse
}