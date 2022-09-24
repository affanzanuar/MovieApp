package com.affan.movieapp.main.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.affan.movieapp.network.ApiService

class DetailsModelFactory(
    private val services: ApiService,
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass){
            DetailsViewModel::class.java -> DetailsViewModel(services) as T
            else -> throw UnsupportedOperationException()
        }
    }


}