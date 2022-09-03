package com.affan.movieapp.view.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.affan.movieapp.data.Data
import com.affan.movieapp.databinding.FragmentHomeBinding
import com.affan.movieapp.model.MoviesOrSeries

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var topMoviesAdapter: TopMoviesAdapter
    private lateinit var handler: Handler

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
        handler = Handler(Looper.myLooper()!!)
        setTopMoviesViewPager()
        getPageChangeCallback()
        setInTheaters()
        setMostPopularMovies()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(getRunnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(getRunnable,3000)
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

    private fun setTopMoviesViewPager() {
        topMoviesAdapter = TopMoviesAdapter(
            Data.itemTopMovies,
            {data: MoviesOrSeries -> getShortToast("Ke details ${data.moviesOrSeriesTitle}") },
            binding.vpTopMovies
        )
        binding.vpTopMovies.adapter = topMoviesAdapter
    }

    private fun setInTheaters () {
        binding.rvInTheatres.adapter = HorizontalListAdapter(
            Data.itemInTheaters
        ) { data: MoviesOrSeries -> getShortToast("ke details ${data.moviesOrSeriesTitle}") }

        binding.rvInTheatres.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun setMostPopularMovies () {
        binding.rvMostPopularMovies.adapter = HorizontalListAdapter(
            Data.itemMostPopularMovies
        ) {data: MoviesOrSeries -> getShortToast("ke details ${data.moviesOrSeriesTitle}") }

        binding.rvMostPopularMovies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun getShortToast(message : String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}