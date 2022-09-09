package com.affan.movieapp.view.main.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.affan.movieapp.databinding.FragmentMoviesBinding
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
        moviesPresenter.getMovies()
    }

    private fun setMoviesAdapter() {
        moviesAdapter = MoviesAdapter()
        binding.rvMovies.adapter = moviesAdapter
        binding.rvMovies.layoutManager = GridLayoutManager(context, 2)
    }

    private fun createPresenter() {
        moviesPresenter = MoviesPresenterImpl(this)
    }

    override fun onReceiveMovies(movies: List<MoviesData>) {
        moviesAdapter.setData(movies)
    }
}