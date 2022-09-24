package com.affan.movieapp.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    private val apiService: ApiService,
) : ViewModel() {

    private val _defaultCategory = "movies"

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _detailResponse: MutableLiveData<DetailsMovieResponse> = MutableLiveData()
    val detailResponse: LiveData<DetailsMovieResponse> = _detailResponse

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getDetailsMovie(id: Int, category: String) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.Main) {
                    _loading.value = true
                    if (category == _defaultCategory) {
                        apiService.getMovieDetails(id, Data.apiKey)
                    } else {
                        apiService.getTvDetails(id, Data.apiKey)
                    }
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _detailResponse.value = data
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = error.message
//                    if (error.message == "404"){
//                        apiService.getMovieDetails(id, Data.apiKey)
//                    }
                }
            }
        }
    }

}

