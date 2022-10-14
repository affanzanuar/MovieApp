package com.affan.movieapp.main.account.myfavorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.favorite.FavoriteMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _cinemaFavorite = MutableLiveData<List<FavoriteMovies>>()
    val cinemaFavorite : LiveData<List<FavoriteMovies>> = _cinemaFavorite

    private val _deleteFavorite = MutableLiveData<Unit>()
    val deleteFavorite : LiveData<Unit> = _deleteFavorite

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun getDataFavorite () {
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getFavorite()
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _cinemaFavorite.value = data
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

    fun deleteDataFavorite (favoriteMovies : FavoriteMovies){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO) {
                    repository.deleteFavorite(favoriteMovies)
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _deleteFavorite.value = data
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

}