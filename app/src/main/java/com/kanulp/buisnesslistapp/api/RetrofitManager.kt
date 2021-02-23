package com.kanulp.buisnesslistapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitManager {

    val apiService: ApiService

    init {

        val client = OkHttpClient.Builder().build()

        apiService = Retrofit.Builder()
            .baseUrl("https://api.yelp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }


}