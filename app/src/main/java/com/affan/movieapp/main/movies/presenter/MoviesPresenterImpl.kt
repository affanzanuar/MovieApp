package com.affan.movieapp.main.movies.presenter

import android.util.Log
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.data.remote.network.ApiClient
import com.affan.movieapp.main.movies.presenter.MoviesPresenter
import com.affan.movieapp.main.movies.presenter.MoviesView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Deprecated("IMPLEMENT MVVM")
class MoviesPresenterImpl(
    private val moviesView: MoviesView,
    private val coroutineScope: CoroutineScope
) : MoviesPresenter {
    override fun getPopularMovies() {
//        coroutineScope.launch {
//            withContext(Dispatchers.IO) {
//                ApiClient.instance.getMostPopularMovie(Data.apiKey)
//                    .enqueue(object : Callback<MovieResponse> {
//                        override fun onResponse(
//                            call: Call<MovieResponse>,
//                            response: Response<MovieResponse>
//                        ) {
//                            val body = response.body()!!
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main) {
//                                    body.results
//                                        .let {
//                                            if (it != null) {
//                                                moviesView.onSuccessGetPopularMovies(it)
//                                                Log.d(
//                                                    "Main Presenter adalah",
//                                                    response.body()?.results.toString()
//                                                )
//                                            }
//                                        }
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main) {
//                                    moviesView.onFailGetPopularMovies(t.message!!)
//                                }
//                            }
//                        }
//                    })
//            }
//        }
    }

}