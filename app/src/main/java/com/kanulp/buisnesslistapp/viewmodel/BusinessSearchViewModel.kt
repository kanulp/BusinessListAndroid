package com.kanulp.buisnesslistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanulp.buisnesslistapp.repo.BusinessRepository
import kotlinx.coroutines.launch

class BusinessSearchViewModel : ViewModel(){

    private val mainRepository = BusinessRepository()

    val businessSearchSuccessLiveData = mainRepository.businessSearchSuccessLiveData
    val businessSearchFailureLiveData = mainRepository.businessSearchFailLiveData

    fun getBusiness(text:String,lat :String,long:String) {
        //this is coroutine viewmodel scope to call suspend fun of repo
        viewModelScope.launch { mainRepository.getBusiness(text,lat,long) }
    }

}