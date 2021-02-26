package com.kanulp.buisnesslistapp.api

import androidx.lifecycle.MutableLiveData
import com.kanulp.buisnesslistapp.model.BusinessSearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization:Bearer pVva0HKnFfjdSIbBOCWzFCbNxuASTen5_wrv-ZgKG-q3LNkO974r-zSc4Qla96_NmMhnYxuBdqxBzqp76rGRZzact7Rqj_bBKNJ5_sYpn7KgvbDY6tlPJ9FgIvcuYHYx")
    @GET("/v3/autocomplete")
    suspend fun getBusinesses(@Query("text") text:String,@Query("latitude")lat : String,@Query("longitude")long : String): Response<BusinessSearchModel>
}
