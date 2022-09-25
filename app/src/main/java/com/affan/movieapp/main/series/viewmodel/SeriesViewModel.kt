package com.affan.movieapp.main.series.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.series.SeriesResponse
import kotlinx.coroutines.launch

class SeriesViewModel(private val repository: Repository) : ViewModel() {
    private val _series = MutableLiveData<SeriesResponse>()
    val errorMessage = MutableLiveData<String>()
    val series: LiveData<SeriesResponse>
        get() = _series

    init {
        viewModelScope.launch {
            val response = repository.getPopularSeries(1, Data.apiKey)
            if (response.isSuccessful) {
                _series.postValue(response.body())
            } else {
                errorMessage.postValue(response.message())
            }
        }
    }
    fun getPopularSeries(page: Int){
        viewModelScope.launch {
            val response = repository.getPopularSeries(page, Data.apiKey)
            if (response.isSuccessful) {
                _series.postValue(response.body())
            }else{
                errorMessage.postValue(response.message())
            }
        }
    }
}