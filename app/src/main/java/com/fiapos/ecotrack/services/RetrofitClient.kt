package com.fiapos.ecotrack.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val api: ClimatiqService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.climatiq.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClimatiqService::class.java)
    }

}