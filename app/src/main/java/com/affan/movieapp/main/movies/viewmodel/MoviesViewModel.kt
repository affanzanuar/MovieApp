package com.affan.movieapp.main.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.main.movies.MoviesRepository
import com.affan.movieapp.model.movie.MovieResponse
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val _movies = MutableLiveData<MovieResponse>()
    val errorMessage = MutableLiveData<String>()
    val series: LiveData<MovieResponse>
        get() = _movies

    init {
        viewModelScope.launch {
            val response = MoviesRepository().getPopularMovies()
            if (response.isSuccessful) {
                _movies.postValue(response.body())
            } else {
                errorMessage.postValue(response.message())
            }
        }
    }
}