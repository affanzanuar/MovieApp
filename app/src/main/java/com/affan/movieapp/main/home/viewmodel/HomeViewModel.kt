package com.affan.movieapp.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.trending.Trending
import com.affan.movieapp.model.trending.TrendingResponse
import com.affan.movieapp.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _trending : MutableLiveData<List<Trending?>> = MutableLiveData()
    val trending : LiveData<List<Trending?>> = _trending

    private val _errorMessage : MutableLiveData<String> = MutableLiveData()
    val errorMessage : LiveData<String> = _errorMessage


    fun getTrending(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                ApiClient.instance.getTopMoviesOrSeries(Data.apiKey)
                    .enqueue(object : Callback<TrendingResponse> {
                        override fun onResponse(
                            call: Call<TrendingResponse>,
                            response: Response<TrendingResponse>
                        ) {
                            val body = response.body()!!
                            Log.d("Home Presenter body",
                                body.toString())
                            viewModelScope.launch {
                                withContext(Dispatchers.Main){
                                    body.results
                                        .let {
                                            if (it != null) {
                                                _trending.value = it
                                                Log.d("Home Presenter adalah",
                                                    it.toString())
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main){
                                    _errorMessage.value = t.message
                                }
                            }
                        }
                    })
            }
        }
    }

}