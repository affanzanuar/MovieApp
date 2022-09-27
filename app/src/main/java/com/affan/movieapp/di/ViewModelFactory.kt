package com.affan.movieapp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.affan.movieapp.data.local.LocalDataSource
import com.affan.movieapp.data.local.room.MovieDatabase
import com.affan.movieapp.data.remote.RemoteDataSource
import com.affan.movieapp.domain.RepositoryImp
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.main.account.myfavorite.viewmodel.FavoriteViewModel
import com.affan.movieapp.main.details.DetailsViewModel
import com.affan.movieapp.main.home.viewmodel.HomeViewModel
import com.affan.movieapp.main.movies.viewmodel.MoviesViewModel
import com.affan.movieapp.main.series.viewmodel.SeriesViewModel
import com.affan.movieapp.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            FavoriteViewModel::class.java -> FavoriteViewModel(repository) as T
            else -> throw UnsupportedOperationException()
        }
    }

    companion object {

        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private val logging : HttpLoggingInterceptor
            get() {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                return httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            }

        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        private val remoteDataSource : ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ApiService::class.java)
        }

        private val remote = RemoteDataSource(remoteDataSource)

        @Volatile
        private var INSTANCE : ViewModelFactory? = null
        fun getInstance(context : Context)= synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(
                RepositoryImp(
                    remoteDataSource = remote,
                    localDataSource = LocalDataSource(MovieDatabase.getInstance(context))
                )
            ). also { INSTANCE = it }
        }
    }
}