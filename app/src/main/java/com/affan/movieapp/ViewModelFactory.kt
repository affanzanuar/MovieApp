package com.affan.movieapp

import androidx.lifecycle.ViewModelProvider
import com.affan.movieapp.main.home.domain.Repository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Repository,
) : ViewModelProvider.Factory