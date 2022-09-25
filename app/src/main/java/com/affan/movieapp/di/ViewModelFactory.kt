package com.affan.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.affan.movieapp.data.remote.RemoteDataSource
import com.affan.movieapp.domain.RepositoryImp
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.main.home.viewmodel.HomeViewModel
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

//        private val moviesDao = MovieDaoImp()

        private val remote = RemoteDataSource(remoteDataSource)
//        private val local = LocalDataSource(moviesDao)

        @Volatile
        private var INSTANCE : ViewModelFactory? = null
        fun getInstance()= synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(
                RepositoryImp(
                    remoteDataSource = remote
                )
            ). also { INSTANCE = it }
        }
    }
}