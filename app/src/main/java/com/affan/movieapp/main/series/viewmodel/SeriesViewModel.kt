package com.affan.movieapp.main.series.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.series.Series
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeriesViewModel(private val repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _series = MutableLiveData<MutableList<Series?>?>()
    val series : LiveData<MutableList<Series?>?> = _series

    var page = 1

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun getPopularSeries(){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getMostPopularSeries(Data.apiKey, page)
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _series.value = data.series
                    page++
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _errorMessage.value = error.message
                    _isLoading.value = false
                }
            }
        }
    }

}