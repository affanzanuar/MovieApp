package com.affan.movieapp.presentation.main.movies.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.affan.movieapp.databinding.FragmentMoviesBinding
import com.affan.movieapp.di.ViewModelFactory
import com.affan.movieapp.presentation.main.details.view.DetailsActivity
import com.affan.movieapp.presentation.main.home.view.HomeFragment
import com.affan.movieapp.presentation.main.movies.adapter.MoviesAdapter
import com.affan.movieapp.presentation.main.movies.viewmodel.MoviesViewModel
import com.affan.movieapp.presentation.main.paginate.PaginationRecyclerView
import com.affan.movieapp.data.model.movie.Movie


class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    private lateinit var moviesAdapter: MoviesAdapter

    private val viewModel: MoviesViewModel by activityViewModels(
        factoryProducer = {
            ViewModelFactory.getInstance(requireContext())
        }
    )

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
        getObserve()
        moviesAdapter.clearData()
        viewModel.pageMovies = 1
        viewModel.getPopularMovies()
    }

    private fun getObserve(){
        viewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (!isLoading){
                binding.skMoviesFragment.visibility = View.GONE
            }
        }

        viewModel.movies.observe(requireActivity()) { data ->
            moviesAdapter.addAll(data!!)
        }
        viewModel.errorMessage.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setMoviesAdapter() {
        moviesAdapter = MoviesAdapter { data: Movie ->
            intentToDetails(data)
        }
        binding.rvMovies.adapter = moviesAdapter
        val layoutManager = GridLayoutManager(context, 2)

        binding.rvMovies.layoutManager = layoutManager

        binding.rvMovies.addOnScrollListener(
            object : PaginationRecyclerView({viewModel.getPopularMovies()}){})
    }

    private fun intentToDetails(movies: Movie) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(HomeFragment.CATEGORY, "movies")
        intent.putExtra(HomeFragment.ID,movies.id)
        startActivity(intent)
    }
}