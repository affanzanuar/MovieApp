package com.affan.movieapp.main.series.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.series.Series
import com.affan.movieapp.model.series.SeriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeriesViewModel(private val repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _series = MutableLiveData<List<Series?>?>()
    val series : LiveData<List<Series?>?> = _series

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun getPopularSeries(page: Int){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getMostPopularSeries(Data.apiKey, page).series
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _series.value = data
                    _isLoading.value = false
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _errorMessage.value = error.message
                    _isLoading.value = false
                }
            }
        }
    }

//    init {
//        viewModelScope.launch {
//            val response = repository.getPopularSeries(1, Data.apiKey)
//            if (response.isSuccessful) {
//                _series.postValue(response.body())
//            } else {
//                errorMessage.postValue(response.message())
//            }
//        }
//    }
//    fun getPopularSeriess(page: Int){
//        viewModelScope.launch {
//            val response = repository.getPopularSeries(page, Data.apiKey)
//            if (response.isSuccessful) {
//                _series.postValue(response.body())
//            }else{
//                errorMessage.postValue(response.message())
//            }
//        }
//    }
}