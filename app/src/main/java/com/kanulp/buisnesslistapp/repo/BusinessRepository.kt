package com.kanulp.buisnesslistapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kanulp.buisnesslistapp.api.RetrofitManager
import com.kanulp.buisnesslistapp.model.BusinessSearchModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class BusinessRepository {

    private val apiService = RetrofitManager.apiService

    val businessSearchSuccessLiveData : MutableLiveData<MutableLiveData<BusinessSearchModel>> = MutableLiveData()
    val businessSearchFailLiveData : MutableLiveData<Boolean> = MutableLiveData()

    /*
    this fun is suspend fun means it will execute in different thread
     */
    suspend fun getBusiness(text:String,lat:String,long:String) {

        try {
            val response = apiService.getBusinesses(text,lat,long)

            Log.d(TAG, "$response")

            if (response.isSuccessful) {
                Log.d(TAG, "SUCCESS")
                //Log.d(TAG, "${response.body()}")
                businessSearchSuccessLiveData.postValue(response.body())

            } else {
                Log.d(TAG, "FAILURE")
                Log.d(TAG, "${response.body()}")
                businessSearchFailLiveData.postValue(true)
            }

        } catch (e: UnknownHostException) {
            Log.e(TAG, e.message.toString())
            //this exception occurs when there is no internet connection or host is not available
            //so inform user that something went wrong
            businessSearchFailLiveData.postValue(true)
        } catch (e: SocketTimeoutException) {
            Log.e(TAG, e.message.toString())
            //this exception occurs when time out will happen
            //so inform user that something went wrong
            businessSearchFailLiveData.postValue(true)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            //this is generic exception handling
            //so inform user that something went wrong
            businessSearchFailLiveData.postValue(true)
        }

    }

    companion object {
        val TAG = BusinessRepository::class.java.simpleName
    }
}