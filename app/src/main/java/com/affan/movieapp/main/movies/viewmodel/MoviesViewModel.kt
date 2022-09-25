package com.affan.movieapp.main.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.movie.MovieResponse
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: Repository) : ViewModel() {
    private val _movies = MutableLiveData<MovieResponse>()
    val errorMessage = MutableLiveData<String>()
    val movies: LiveData<MovieResponse>
        get() = _movies

    init {
        viewModelScope.launch {

            val response = repository.getPopularMovies(1, Data.apiKey)
            if (response.isSuccessful) {
                _movies.postValue(response.body())
            } else {
                errorMessage.postValue(response.message())
            }
        }
    }
    fun getPopularMovies(page: Int){
        viewModelScope.launch {
            val response = repository.getPopularMovies(page, Data.apiKey)
            if (response.isSuccessful) {
                _movies.postValue(response.body())
            }else{
                errorMessage.postValue(response.message())
            }
        }
    }
}