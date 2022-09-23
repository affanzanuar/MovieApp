package com.affan.movieapp.model.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.view.main.series.SeriesRepository
import kotlinx.coroutines.launch

class SeriesResponseViewModel : ViewModel() {
    val _series = MutableLiveData<SeriesResponse>()
    val errorMessage = MutableLiveData<String>()
    val series: LiveData<SeriesResponse>
        get() = _series

    init {
        viewModelScope.launch {
            val response = SeriesRepository().getPopularSeries()
            if (response.isSuccessful) {
                _series.postValue(response.body())
            }else{
                errorMessage.postValue(response.message())
            }
        }
    }
}