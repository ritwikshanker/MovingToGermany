package com.moving.germany.base

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun createInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api-free.deepl.com/v2")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}