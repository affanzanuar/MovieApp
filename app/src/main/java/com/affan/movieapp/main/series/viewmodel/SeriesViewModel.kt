package com.affan.movieapp.main.series.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.main.series.SeriesRepository
import kotlinx.coroutines.launch

class SeriesViewModel(private val repository: Repository) : ViewModel() {
    private val _series = MutableLiveData<SeriesResponse>()
    val errorMessage = MutableLiveData<String>()
    val series: LiveData<SeriesResponse>
        get() = _series

    init {
        viewModelScope.launch {
            val response = SeriesRepository().getPopularSeries(1)
            if (response.isSuccessful) {
                _series.postValue(response.body())
            } else {
                errorMessage.postValue(response.message())
            }
        }
    }
    fun getPopularSeries(page: Int){
        viewModelScope.launch {
            val response = SeriesRepository().getPopularSeries(page)
            if (response.isSuccessful) {
                _series.postValue(response.body())
            }else{
                errorMessage.postValue(response.message())
            }
        }
    }
}