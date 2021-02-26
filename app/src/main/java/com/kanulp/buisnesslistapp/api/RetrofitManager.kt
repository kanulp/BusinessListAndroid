package com.kanulp.buisnesslistapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitManager {

    val apiService: ApiService
    var logging : HttpLoggingInterceptor = HttpLoggingInterceptor();

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY;

        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        apiService = Retrofit.Builder()
            .baseUrl("https://api.yelp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }


}