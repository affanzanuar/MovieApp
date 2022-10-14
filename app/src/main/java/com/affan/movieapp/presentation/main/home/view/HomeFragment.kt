package com.affan.movieapp.presentation.main.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.affan.movieapp.databinding.FragmentHomeBinding
import com.affan.movieapp.di.ViewModelFactory
import com.affan.movieapp.presentation.main.details.view.DetailsActivity
import com.affan.movieapp.presentation.main.home.adapter.ComingSoonAdapter
import com.affan.movieapp.presentation.main.home.adapter.HomeMoviesAdapter
import com.affan.movieapp.presentation.main.home.adapter.HomeSeriesAdapter
import com.affan.movieapp.presentation.main.home.adapter.TrendingAdapter
import com.affan.movieapp.presentation.main.home.viewmodel.HomeViewModel
import com.affan.movieapp.data.model.comingsoon.ComingSoon
import com.affan.movieapp.data.model.movie.Movie
import com.affan.movieapp.data.model.series.Series
import com.affan.movieapp.data.model.trending.Trending
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private lateinit var trendingAdapter: TrendingAdapter

    private lateinit var inTheaterAdapter: HomeMoviesAdapter

    private lateinit var mostPopularMovieAdapter: HomeMoviesAdapter

    private lateinit var mostPopularSeriesAdapter: HomeSeriesAdapter

    private lateinit var comingSoonAdapter: ComingSoonAdapter

    private lateinit var homeViewModel: HomeViewModel

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
        homeViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext()))[HomeViewModel::class.java]
        trendingAdapter = TrendingAdapter { data: Trending -> intentTrendingToDetails(data) }
        binding.vpTopMovies.adapter = trendingAdapter
        inTheaterAdapter = setMovieAdapter(binding.rvInTheatres)
        mostPopularMovieAdapter = setMovieAdapter(binding.rvMostPopularMovies)
        mostPopularSeriesAdapter= setSeriesAdapter(binding.rvMostPopularSeries)
        comingSoonAdapter = setComingSoonAdapter(binding.rvComingSoon)
        getObserveLiveData()
        homeViewModel.getTrending()
        homeViewModel.getInTheater()
        homeViewModel.getPopularMovies()
        homeViewModel.getPopularSeries()
        homeViewModel.getComingSoon()
        homeViewModel.viewModelScope.launch {
            withContext(Dispatchers.Main){
                binding.vpTopMovies.scrollIndefinitely(5000)
            }
        }
    }

    private suspend fun ViewPager2.scrollIndefinitely(interval : Long) {
        delay(interval)
        val numberOfItems = adapter?.itemCount ?: 0
        val lasIndex = if (numberOfItems>0) numberOfItems-1 else 0
        val nextItem = if (currentItem==lasIndex) 0 else currentItem + 1
        setCurrentItem(nextItem,true)
        scrollIndefinitely(interval)
    }

    private fun getObserveLiveData(){
        homeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading){
                binding.cvTranding.visibility = View.GONE
                binding.tvInTheatres.visibility = View.GONE
                binding.tvMostPopularMovies.visibility = View.GONE
                binding.tvMostPopularSeries.visibility = View.GONE
                binding.tvComingSoon.visibility = View.GONE
                binding.tvSeAllInTheatres.visibility = View.GONE
                binding.tvSeeAllMostPopularMovies.visibility = View.GONE
                binding.tvSeeAllMostPopularSeries.visibility = View.GONE
                binding.tvSeeAllComingSoon.visibility = View.GONE
                binding.skHomeFragment.visibility = View.VISIBLE
            } else {
                binding.cvTranding.visibility = View.VISIBLE
                binding.tvInTheatres.visibility = View.VISIBLE
                binding.tvMostPopularMovies.visibility = View.VISIBLE
                binding.tvMostPopularSeries.visibility = View.VISIBLE
                binding.tvComingSoon.visibility = View.VISIBLE
                binding.tvSeAllInTheatres.visibility = View.VISIBLE
                binding.tvSeeAllMostPopularMovies.visibility = View.VISIBLE
                binding.tvSeeAllMostPopularSeries.visibility = View.VISIBLE
                binding.tvSeeAllComingSoon.visibility = View.VISIBLE
                binding.skHomeFragment.visibility = View.GONE
            }
        }



        homeViewModel.trending.observe(viewLifecycleOwner) { data ->
            trendingAdapter.setDataTrending(data)
            binding.ciTrending.setViewPager(binding.vpTopMovies)
            Log.d("Home Fragment",data.toString())
        }

        homeViewModel.inTheater.observe(viewLifecycleOwner) { data ->
            inTheaterAdapter.setDataMovies(data)
            Log.d("Home Fragment",data.toString())
        }

        homeViewModel.popularMovies.observe(viewLifecycleOwner) { data ->
            mostPopularMovieAdapter.setDataMovies(data)
            Log.d("Home Fragment",data.toString())
        }

        homeViewModel.popularSeries.observe(viewLifecycleOwner) { data ->
            mostPopularSeriesAdapter.setDataSeries(data)
            Log.d("Home Fragment",data.toString())
        }

        homeViewModel.comingSoon.observe(viewLifecycleOwner) { data ->
            comingSoonAdapter.setDataComingSoon(data)
            Log.d("Home Fragment",data.toString())
        }

        homeViewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            getShortToast(error)
        }

    }

    private fun setMovieAdapter (rv : RecyclerView) : HomeMoviesAdapter {
         val moviesAdapter = HomeMoviesAdapter {
                data: Movie -> intentMoviesToDetails(data)
        }
        rv.adapter = moviesAdapter
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        return moviesAdapter
    }

    private fun setSeriesAdapter (rv : RecyclerView) : HomeSeriesAdapter {
        val seriesAdapter = HomeSeriesAdapter {
                data: Series -> intentSeriesToDetails(data)
        }
        rv.adapter = seriesAdapter
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        return seriesAdapter
    }

    private fun setComingSoonAdapter (rv : RecyclerView) : ComingSoonAdapter {
        val comingSoonAdapter = ComingSoonAdapter {
                data: ComingSoon -> intentComingSoonToDetails(data)
        }
        rv.adapter = comingSoonAdapter
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        return comingSoonAdapter
    }

    private fun intentTrendingToDetails ( item : Trending) {
        val intent = Intent(context, DetailsActivity::class.java)
        if (item.title.isNullOrEmpty()){
            intent.putExtra(CATEGORY,"series")
        } else {
            intent.putExtra(CATEGORY,"movies")
        }
        intent.putExtra(ID,item.id)
        startActivity(intent)
    }

    private fun intentMoviesToDetails ( item : Movie) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(CATEGORY,"movies")
        intent.putExtra(ID,item.id)
        startActivity(intent)
    }

    private fun intentSeriesToDetails ( item : Series) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(CATEGORY,"series")
        intent.putExtra(ID,item.id)
        startActivity(intent)
    }

    private fun intentComingSoonToDetails ( item : ComingSoon) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(CATEGORY,"movies")
        intent.putExtra(ID,item.id)
        startActivity(intent)
    }

    private fun getShortToast(message : String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_DATA_MS = "extra data movies or series"
        const val CATEGORY = "category"
        const val ID = "id"
    }
}