package com.fiapos.ecotrack.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val api: Service by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.climatiq.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }
}