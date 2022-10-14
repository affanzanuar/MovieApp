package com.affan.movieapp.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Utility
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.data.model.comingsoon.ComingSoon
import com.affan.movieapp.data.model.movie.Movie
import com.affan.movieapp.data.model.series.Series
import com.affan.movieapp.data.model.trending.Trending
import kotlinx.coroutines.*

class HomeViewModel (
    private val repository: Repository
        ) : ViewModel() {

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean> = _isLoading

    private val page : Int = 1

    private val _trending : MutableLiveData<List<Trending?>> = MutableLiveData()
    val trending : LiveData<List<Trending?>> = _trending

    private val _inTheater : MutableLiveData<List<Movie?>> = MutableLiveData()
    val inTheater : LiveData<List<Movie?>> = _inTheater

    private val _popularMovies : MutableLiveData<List<Movie?>> = MutableLiveData()
    val popularMovies : LiveData<List<Movie?>> = _popularMovies

    private val _popularSeries : MutableLiveData<List<Series?>> = MutableLiveData()
    val popularSeries : LiveData<List<Series?>> = _popularSeries

    private val _comingSoon : MutableLiveData<List<ComingSoon?>> = MutableLiveData()
    val comingSoon : LiveData<List<ComingSoon?>> = _comingSoon

    private val _errorMessage : MutableLiveData<String> = MutableLiveData()
    val errorMessage : LiveData<String> = _errorMessage

    fun getTrending(){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getTopMoviesOrSeries(Utility.apiKey).results
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _trending.value = data
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

    fun getInTheater(){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getNowPlaying(Utility.apiKey).results
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _inTheater.value = data
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


    fun getPopularMovies(){
       viewModelScope.launch {
           runCatching {
               _isLoading.value = true
               withContext(Dispatchers.IO){
                   repository.getMostPopularMovie(Utility.apiKey, page).results
               }
           }.onSuccess { data ->
               withContext(Dispatchers.Main){
                   _popularMovies.value = data
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

    fun getPopularSeries(){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getMostPopularSeries(Utility.apiKey, page).series
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main) {
                    _popularSeries.value = data
                    _isLoading.value = false
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main) {
                    _errorMessage.value = error.message
                    _isLoading.value = false
                }
            }
        }
    }

    fun getComingSoon(){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getComingSoon(
                        Utility.apiKey,
                        Utility.language,
                        Utility.sortBy,
                        Utility.page,
                        Utility.releaseDateGte,
                        Utility.releaseDateLte,
                        Utility.monetizationTypes
                    ).results
                }
            }.onSuccess { data ->
                _comingSoon.value = data
                _isLoading.value = false
            }.onFailure { error ->
                _errorMessage.value = error.message
                _isLoading.value = false
            }
        }
    }
}