package com.affan.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.main.details.DetailsViewModel
import com.affan.movieapp.main.home.viewmodel.HomeViewModel
import com.affan.movieapp.main.movies.viewmodel.MoviesViewModel
import com.affan.movieapp.main.series.viewmodel.SeriesViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Repository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            HomeViewModel::class.java -> HomeViewModel(repository) as T
            DetailsViewModel::class.java -> DetailsViewModel(repository) as T
            SeriesViewModel::class.java -> SeriesViewModel(repository) as T
            MoviesViewModel::class.java -> MoviesViewModel(repository) as T
            else -> throw UnsupportedOperationException()
        }
    }

    companion object {

        @Volatile
        private var INSTANCE : ViewModelFactory? = null
        fun getInstance()= synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(
            repository = provideRepository(
                remoteDataSource = provideRemoteDataSource(
                    apiService = provideApiService(
                        retrofit = provideRetrofit(
                            gsonConverterFactory = provideGsonConverterFactory(),
                            okHttpClient = provideOkHttpClient(
                                httpLoggingInterceptor = provideHttpLoggingInterceptor()
                            )
                        )
                    )
                )
            )
            ). also { INSTANCE = it }
        }
    }
}