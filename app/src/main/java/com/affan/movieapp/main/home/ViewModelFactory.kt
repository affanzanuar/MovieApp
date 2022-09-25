package com.affan.movieapp.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.affan.movieapp.main.details.DetailsViewModel
import com.affan.movieapp.main.home.domain.Repository
import com.affan.movieapp.main.home.viewmodel.HomeViewModel
import com.affan.movieapp.network.ApiClient
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

        private val instance : ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ApiService::class.java)
        }

        @Volatile
        private var INSTANCE : ViewModelFactory? = null
        fun getInstance()= synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(
                RepositoryImp(instance)
            ). also { INSTANCE = it }
        }
    }

}