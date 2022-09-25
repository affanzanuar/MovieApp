package com.affan.movieapp.main.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.affan.movieapp.databinding.FragmentHomeBinding
import com.affan.movieapp.main.details.DetailsActivity
import com.affan.movieapp.main.home.RepositoryImp
import com.affan.movieapp.main.home.ViewModelFactory
import com.affan.movieapp.main.home.adapter.ComingSoonAdapter
import com.affan.movieapp.main.home.adapter.HomeMoviesAdapter
import com.affan.movieapp.main.home.adapter.HomeSeriesAdapter
import com.affan.movieapp.main.home.adapter.TrendingAdapter
import com.affan.movieapp.main.home.viewmodel.HomeViewModel
import com.affan.movieapp.model.comingsoon.ComingSoon
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.model.series.Series
import com.affan.movieapp.model.trending.Trending
import com.affan.movieapp.network.ApiClient
import com.affan.movieapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var inTheaterAdapter: HomeMoviesAdapter
    private lateinit var mostPopularMovieAdapter: HomeMoviesAdapter
    private lateinit var mostPopularSeriesAdapter: HomeSeriesAdapter
    private lateinit var comingSoonAdapter: ComingSoonAdapter

    private val homeViewModel: HomeViewModel by activityViewModels(
        factoryProducer = {
            ViewModelFactory.getInstance()
        }
    )

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
        val intent = Intent(context,DetailsActivity::class.java)
        val parcelable = Trending (
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
        if (item.title.isNullOrEmpty()){
            intent.putExtra(CATEGORY,"series")
        } else {
            intent.putExtra(CATEGORY,"movies")
        }
        intent.putExtra(ID,item.id)
        startActivity(intent)
    }

    private fun intentMoviesToDetails ( item : Movie) {
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
        intent.putExtra(CATEGORY,"movies")
        intent.putExtra(ID,item.id)
        startActivity(intent)
    }

    private fun intentSeriesToDetails ( item : Series) {
        val intent = Intent(context,DetailsActivity::class.java)
        val parcelable = Series (
            item.backdropPath,
            item.firstAirDate,
            item.genreIds,
            item.id,
            item.name,
            item.originCountry,
            item.originalLanguage,
            item.originalName,
            item.overview,
            item.popularity,
            item.posterPath,
            item.voteAverage,
            item.voteCount
        )
        intent.putExtra(EXTRA_DATA_MS,parcelable)
        intent.putExtra(CATEGORY,"series")
        intent.putExtra(ID,item.id)
        startActivity(intent)
    }

    private fun intentComingSoonToDetails ( item : ComingSoon) {
        val intent = Intent(context,DetailsActivity::class.java)
        val parcelable = ComingSoon (
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