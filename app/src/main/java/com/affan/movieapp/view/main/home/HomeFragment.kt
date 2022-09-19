package com.affan.movieapp.view.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.affan.movieapp.databinding.FragmentHomeBinding
import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.model.trending.MoviesSeries
import com.affan.movieapp.view.main.details.DetailsActivity
import com.affan.movieapp.view.main.home.adapter.HorizontalListAdapter
import com.affan.movieapp.view.main.home.adapter.TopMoviesAdapter
import com.affan.movieapp.view.main.home.presenter.HomeView

class HomeFragment : Fragment(), HomeView {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var topMoviesAdapter: TopMoviesAdapter
    private lateinit var horizontalListAdapter: HorizontalListAdapter
    private lateinit var handler: Handler
    private lateinit var homePresenter: HomePresenterImp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createPresenter()
        handler = Handler(Looper.myLooper()!!)
        setTopMoviesViewPager()
        homePresenter.getTopMoviesOrSeries()
        getPageChangeCallback()
        setHorizontalListAdapter(binding.rvInTheatres)
        homePresenter.getInTheaters()
        setHorizontalListAdapter(binding.rvMostPopularMovies)
        homePresenter.getMostPopularMovies()
//        setHorizontalListAdapter(binding.rvMostPopularSeries)
//        homePresenter.getMostPopularSeries()
//        setHorizontalListAdapter(binding.rvComingSoon)
//        homePresenter.getComingSoon()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(getRunnable)
    }

    override fun onResume() {
        super.onResume()
        homePresenter.getTopMoviesOrSeries()
        handler.postDelayed(getRunnable,3000)
//        homePresenter.getInTheaters()
//        homePresenter.getMostPopularMovies()
//        homePresenter.getMostPopularSeries()
//        homePresenter.getComingSoon()
    }

    private fun getPageChangeCallback () {
        binding.vpTopMovies.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(getRunnable)
                handler.postDelayed(getRunnable,3000)
            }
        })
    }

    private val getRunnable = Runnable {
        binding.vpTopMovies.currentItem = binding.vpTopMovies.currentItem + 1
    }

    private fun createPresenter (){
        homePresenter = HomePresenterImp(this,lifecycleScope)
    }

    private fun setTopMoviesViewPager() {
        topMoviesAdapter = TopMoviesAdapter(
            {data: MoviesSeries -> intentTopMsToDetails(data) },
            binding.vpTopMovies
        )
        binding.vpTopMovies.adapter = topMoviesAdapter
    }

    private fun setHorizontalListAdapter (rv : RecyclerView) {
        horizontalListAdapter = HorizontalListAdapter {
                data: Movie -> intentToDetails(data)
        }
        rv.adapter = horizontalListAdapter
        rv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun intentTopMsToDetails ( item : MoviesSeries) {
        val intent = Intent(context,DetailsActivity::class.java)
        val parcelable = MoviesSeries (
            item.adult,
            item.backdropPath,
            item.firstAirDate,
            item.genreIds,
            item.id,
            item.mediaType,
            item.name,
            item.originCountry,
            item.originalLanguage,
            item.originalName,
            item.originalTitle,
            item.overview,
            item.popularity,
            item.posterPath,
            item.releaseDate,
            item.title,
            item.video,
            item.voteAverage,
            item.voteCount
        )
        intent.putExtra(EXTRA_DATA_MS,parcelable)
        startActivity(intent)
    }

    private fun intentToDetails ( item : Movie) {
        val intent = Intent(context,DetailsActivity::class.java)
        val parcelable = Movie (
            item.adult,
            item.backdropPath,
            item.genreIds,
            item.id,
            item.originalLanguage,
            item.originalTitle,
            item.overview,
            item.popularity,
            item.posterPath,
            item.releaseDate,
            item.title,
            item.video,
            item.voteAverage,
            item.voteCount
        )
        intent.putExtra(EXTRA_DATA_MS,parcelable)
        startActivity(intent)
    }

    private fun getShortToast(message : String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
    override fun onSuccessReceiveTopMoviesOrSeries(moviesOrSeries: List<MoviesSeries?>) {
        topMoviesAdapter.setData(moviesOrSeries)
    }

    override fun onFailureReceiveTopMoviesOrSeries(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessReceiveHorizontalList(moviesOrSeries: List<Movie?>) {
        horizontalListAdapter.setData(moviesOrSeries)
    }

    override fun onFailureReceiveHorizontalList(message: String) {
        getShortToast(message)
    }

    companion object {
        const val EXTRA_DATA_MS = "extra data movies or series"
    }
}