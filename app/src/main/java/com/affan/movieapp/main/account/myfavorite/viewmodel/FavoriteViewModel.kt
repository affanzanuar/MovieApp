package com.affan.movieapp.main.account.myfavorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.FavoriteMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(
    private val repository: Repository
) : ViewModel() {

    private val defaultCategory = "movies"

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _cinemaFavorite = MutableLiveData<List<FavoriteMovies>>()
    val cinemaFavorite : LiveData<List<FavoriteMovies>> = _cinemaFavorite

//    private val _insertFavorite = MutableLiveData<Unit>()
//    val insertFavorite : LiveData<Unit> = _insertFavorite

    private val _deleteFavorite = MutableLiveData<Unit>()
    val deleteFavorite : LiveData<Unit> = _deleteFavorite

    fun getDataFavorite () {
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                _cinemaFavorite.value = repository.getFavorite()
            }
        }
    }

//    fun setDataMovies (favoriteMovies: FavoriteMovies){
//        viewModelScope.launch {
//            _insertFavorite.value = repository.insertFavorite(favoriteMovies)
//        }
//    }

    fun deleteDataFavorite (favoriteMovies: FavoriteMovies){
        viewModelScope.launch {
            _deleteFavorite.value = repository.deleteFavorite(favoriteMovies)
        }
    }

}