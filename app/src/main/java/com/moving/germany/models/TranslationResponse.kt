package com.moving.germany.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TranslationResponse(
    @SerializedName("translations")
    val translations: List<Translation>?
)