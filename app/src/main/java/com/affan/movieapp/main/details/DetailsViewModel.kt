package com.affan.movieapp.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.details.videos.VideosResult
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

    private val _videoResponse: MutableLiveData<VideosResponse> = MutableLiveData()
    val videoResponse: LiveData<VideosResponse> = _videoResponse
    private val _videoKey: MutableLiveData<String?> = MutableLiveData()
    val videoKey: LiveData<String?> = _videoKey

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
                }
            }
        }
    }


    fun getVideos(id: Int, category: String) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.Main) {
                    _loading.value = true
                    if (category == _defaultCategory) {
                        apiService.getMovieVideos(id, Data.apiKey)
                    } else {
                        apiService.getTvVideos(id, Data.apiKey)
                    }
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _videoResponse.value = data
                    _videoKey.value =
                        if (data.results.isNullOrEmpty()){
                            "Video is not Available"
                        }else{
                            data.results.orEmpty().takeOfficialTrailer()?.key
                        }
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = error.message
                }
            }
        }
    }

    fun List<VideosResult?>.takeOfficialTrailer(): VideosResult? {
        return filter {
            it?.name == "Official Trailer"
        }.first()
    }
}