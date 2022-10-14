package com.affan.movieapp.main.home.presenter

import kotlinx.coroutines.CoroutineScope

@Deprecated("not used anymore")
class HomePresenterImp(
    private val homeView: HomeView,
    private val coroutineScope : CoroutineScope
    ) : HomePresenter {
    override fun getTopMoviesOrSeries() {
        TODO("Not yet implemented")
    }

//    override fun getTopMoviesOrSeries(){
//        coroutineScope.launch {
//            withContext(Dispatchers.IO){
//                ApiClient.instance.getTopMoviesOrSeries(Data.apiKey)
//                    .enqueue(object : Callback<TrendingResponse> {
//                        override fun onResponse(
//                            call: Call<TrendingResponse>,
//                            response: Response<TrendingResponse>
//                        ) {
//                            val body = response.body()!!
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    body.results
//                                        .let {
//                                            if (it != null) {
//                                                homeView.onSuccessReceiveTopMoviesOrSeries(it)
//                                                Log.d("Main Presenter adalah",
//                                                    response.body()?.results.toString())
//                                            }
//                                        }
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    homeView.onFailureReceiveTopMoviesOrSeries(t.message!!)
//                                }
//                            }
//                        }
//                    })
//            }
//        }
//    }

    override fun getInTheaters(){
//        coroutineScope.launch {
//            withContext(Dispatchers.IO){
//                ApiClient.instance.getNowPlaying(Data.apiKey)
//                    .enqueue(object : Callback<MovieResponse> {
//                        override fun onResponse(
//                            call: Call<MovieResponse>,
//                            response: Response<MovieResponse>
//                        ) {
//                            val body = response.body()!!
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    body.results
//                                        .let {
//                                            if (it != null) {
//                                                homeView.onSuccessGetInTheater(it)
//                                                Log.d("Main Presenter adalah",
//                                                    response.body()?.results.toString())
//                                            }
//                                        }
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    homeView.onFailureGetInTheater(t.message!!)
//                                }
//                            }
//                        }
//                    })
//            }
//        }
    }

    override fun getMostPopularMovies(){
//        coroutineScope.launch {
//            withContext(Dispatchers.IO){
//                ApiClient.instance.getMostPopularMovie(Data.apiKey)
//                    .enqueue(object : Callback<MovieResponse> {
//                        override fun onResponse(
//                            call: Call<MovieResponse>,
//                            response: Response<MovieResponse>
//                        ) {
//                            val body = response.body()!!
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    body.results
//                                        .let {
//                                            if (it != null) {
//                                                homeView.onSuccessGetPopularMovie(it)
//                                                Log.d("Main Presenter adalah",
//                                                    response.body()?.results.toString())
//                                            }
//                                        }
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    homeView.onFailureGetPopularMovie(t.message!!)
//                                }
//                            }
//                        }
//                    })
//            }
//        }
    }

    override fun getMostPopularSeries(){
//        coroutineScope.launch {
//            withContext(Dispatchers.IO){
//                ApiClient.instance.getMostPopularSeries(Data.apiKey)
//                    .enqueue(object : Callback<SeriesResponse> {
//                        override fun onResponse(
//                            call: Call<SeriesResponse>,
//                            response: Response<SeriesResponse>
//                        ) {
//                            val body = response.body()!!
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    body.series
//                                        .let {
//                                            if (it != null) {
//                                                homeView.onSuccessGetPopularSeries(it)
//                                                Log.d("Main Presenter adalah",
//                                                    response.body()?.series.toString())
//                                            }
//                                        }
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    homeView.onFailureGetPopularSeries(t.message!!)
//                                }
//                            }
//                        }
//                    })
//            }
//        }
    }

    override fun getComingSoon(){
//        coroutineScope.launch {
//            withContext(Dispatchers.IO){
//                ApiClient.instance.getComingSoon(
//                    Data.apiKey,
//                    Data.language,
//                    Data.sortBy,
//                    Data.page,
//                    Data.releaseDateGte,
//                    Data.releaseDateLte,
//                    Data.monetizationTypes
//                )
//                    .enqueue(object : Callback<ComingSoonResponse> {
//                        override fun onResponse(
//                            call: Call<ComingSoonResponse>,
//                            response: Response<ComingSoonResponse>
//                        ) {
//                            val body = response.body()!!
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    body.results
//                                        .let {
//                                            if (it != null) {
//                                                homeView.onSuccessGetComingSoon(it)
//                                                Log.d("Main Presenter adalah",
//                                                    response.body()?.results.toString())
//                                            }
//                                        }
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<ComingSoonResponse>, t: Throwable) {
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main){
//                                    homeView.onFailureGetComingSoon(t.message!!)
//                                }
//                            }
//                        }
//                    })
//            }
//        }
    }
}