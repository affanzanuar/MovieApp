package com.affan.movieapp.view.main.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.affan.movieapp.databinding.FragmentMoviesBinding
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.view.main.details.DetailsActivity
import com.affan.movieapp.view.main.home.HomeFragment
import com.affan.movieapp.view.main.movies.adapter.MoviesAdapter
import com.affan.movieapp.view.main.movies.presenter.MoviesPresenter
import com.affan.movieapp.view.main.movies.presenter.MoviesView


class MovieFragment : Fragment(), MoviesView {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var moviesPresenter: MoviesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createPresenter()
        setMoviesAdapter()
        moviesPresenter.getPopularMovies()
    }

    private fun setMoviesAdapter() {
        moviesAdapter = MoviesAdapter { data: Movie ->
            intentToDetails(data)
        }
        binding.rvMovies.adapter = moviesAdapter
        binding.rvMovies.layoutManager = GridLayoutManager(context, 2)
    }

    private fun intentToDetails(movies: Movie) {
        val intent = Intent(context, DetailsActivity::class.java)
        val parcelable = Movie (
            movies.adult,
            movies.backdropPath,
            movies.genreIds,
            movies.id,
            movies.originalLanguage,
            movies.originalTitle,
            movies.overview,
            movies.popularity,
            movies.posterPath,
            movies.releaseDate,
            movies.title,
            movies.video,
            movies.voteAverage,
            movies.voteCount
        )
        intent.putExtra(HomeFragment.EXTRA_DATA_MS,parcelable)
        intent.putExtra("id",movies.id)
        intent.putExtra("category","movies")
        startActivity(intent)
    }

    private fun createPresenter() {
        moviesPresenter = MoviesPresenterImpl(this,lifecycleScope)
    }

    override fun onReceiveMovies(movies: List<MoviesData>) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetPopularMovies(movies: List<Movie?>) {
        moviesAdapter.setData(movies)
    }

    override fun onFailGetPopularMovies(string: String) {
        //Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}