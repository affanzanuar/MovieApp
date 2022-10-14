package com.affan.movieapp.main.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Utility
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.movie.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _movies = MutableLiveData<List<Movie?>?>()
    val movies: LiveData<List<Movie?>?> = _movies

    var pageMovies = 1

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun getPopularMovies(){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getMostPopularMovie(Utility.apiKey, pageMovies)
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _movies.value = data.results
                    _isLoading.value = false
                    pageMovies++
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