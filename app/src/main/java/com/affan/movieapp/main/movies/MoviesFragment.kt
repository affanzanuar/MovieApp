package com.affan.movieapp.main.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.affan.movieapp.databinding.FragmentMoviesBinding
import com.affan.movieapp.main.details.DetailsActivity
import com.affan.movieapp.main.home.view.HomeFragment
import com.affan.movieapp.main.movies.adapter.MoviesAdapter
import com.affan.movieapp.main.movies.viewmodel.MoviesViewModel
import com.affan.movieapp.model.movie.Movie


class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMoviesAdapter()

        moviesViewModel.movies.observe(requireActivity()) {
            it.results?.let { data ->
                moviesAdapter.setData(data)
            }
        }
        moviesViewModel.errorMessage.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }
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
        val parcelable = Movie(
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
        intent.putExtra(HomeFragment.EXTRA_DATA_MS, parcelable)
        intent.putExtra(HomeFragment.CATEGORY, "movies")
        intent.putExtra("id",movies.id)
        intent.putExtra("category","movies")
        startActivity(intent)
    }
}