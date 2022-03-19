package com.moving.germany.translate

import com.moving.germany.base.BaseRepo
import com.moving.germany.models.TranslationResponse

class TranslateRepo : BaseRepo() {

    private val service = retrofit.create(TranslateInterface::class.java)

    suspend fun getTranslatedText(): TranslationResponse {
        return service.getSummary()
    }

}