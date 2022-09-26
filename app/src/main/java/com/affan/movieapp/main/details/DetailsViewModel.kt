package com.affan.movieapp.main.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.data.local.room.FavoriteMovies
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.details.videos.VideosResult
import com.affan.movieapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _defaultCategory = "movies"

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _detailResponse: MutableLiveData<DetailsMovieResponse> = MutableLiveData()
    val detailResponse: LiveData<DetailsMovieResponse> = _detailResponse

    private val _videoKey: MutableLiveData<String?> = MutableLiveData()
    val videoKey: LiveData<String?> = _videoKey

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _insertFavorite = MutableLiveData<Unit>()
    val insertFavorite : LiveData<Unit> = _insertFavorite


    fun setDataMovies (favoriteMovies: FavoriteMovies){
        viewModelScope.launch {
            _insertFavorite.value = repository.insertFavorite(favoriteMovies)
        }
    }

    fun getDetailsMovie(id: Int, category: String) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.Main) {
                    _loading.value = true
                    if (category == _defaultCategory) {
                        repository.getMovieDetails(id, Data.apiKey)
                    } else {
                        repository.getTvDetails(id, Data.apiKey)
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
                        repository.getMovieVideos(id, Data.apiKey)
                    } else {
                        repository.getTvVideos(id, Data.apiKey)
                    }
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _videoKey.value = data.results?.takeOfficialTrailer()?.key
                        ?: data.results?.takeYoutubeSite()?.key
                                ?: "Not Available"

                    Log.d("DetailViewModel Key", _videoKey.value.toString())

                }
            }.onFailure { error ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = error.message
                    _videoKey.value = "Not Available"
                }
            }
        }
    }

    private fun List<VideosResult?>.takeOfficialTrailer(): VideosResult? {
        return firstOrNull {
            it?.name == "Official Trailer"
                    || it?.name == "Official Trailer 1"
                    || it?.name == "Official Trailer 2"
                    || it?.name == "Official Trailer 3"
                    || it?.name == "Official Teaser Trailer"
                    || it?.name == "Official Teaser"
        }
    }

    private fun List<VideosResult?>.takeYoutubeSite(): VideosResult? {
        return firstOrNull {
            it?.site == "YouTube"
        }
    }
}