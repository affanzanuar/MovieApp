package com.affan.movieapp.main.series.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.main.series.SeriesRepository
import kotlinx.coroutines.launch

class SeriesViewModel : ViewModel() {
    private val _series = MutableLiveData<SeriesResponse>()
    val errorMessage = MutableLiveData<String>()
    val series: LiveData<SeriesResponse>
        get() = _series

    init {
        viewModelScope.launch {
            val response = SeriesRepository().getPopularSeries()
            if (response.isSuccessful) {
                _series.postValue(response.body())
            } else {
                errorMessage.postValue(response.message())
            }
        }
    }
}