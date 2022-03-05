package com.moving.germany.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Translation(
    @SerializedName("detected_source_language")
    val detectedSourceLanguage: String?, // EN
    @SerializedName("text")
    val text: String? // Der Tisch ist grün. Der Stuhl ist schwarz.
)