package com.affan.movieapp.presentation.main.movies.presenter

import kotlinx.coroutines.CoroutineScope

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