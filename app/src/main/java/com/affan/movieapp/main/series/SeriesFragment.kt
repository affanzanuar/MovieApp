package com.affan.movieapp.main.series

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.affan.movieapp.databinding.FragmentSeriesBinding
import com.affan.movieapp.di.ViewModelFactory
import com.affan.movieapp.main.details.DetailsActivity
import com.affan.movieapp.main.home.view.HomeFragment
import com.affan.movieapp.main.series.adapter.PaginationRecyclerView
import com.affan.movieapp.main.series.adapter.SeriesAdapter
import com.affan.movieapp.main.series.viewmodel.SeriesViewModel
import com.affan.movieapp.model.series.Series

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private lateinit var seriesAdapter: SeriesAdapter

    private val seriesViewModel: SeriesViewModel by activityViewModels(
        factoryProducer = {
            ViewModelFactory.getInstance()
        }
    )

    private var page = 1
    private var isLoadDataOnProgress = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSeriesAdapter()
        seriesViewModel.series.observe(requireActivity()) {
            binding.skSeriesFragment.visibility = View.VISIBLE
            it.series.let { data ->
                isLoadDataOnProgress = false
                binding.skSeriesFragment.visibility = View.GONE
                seriesAdapter.setData(data)
            }
        }
        seriesViewModel.errorMessage.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSeriesAdapter() {
        seriesAdapter = SeriesAdapter { series: Series ->
            intentToDetails(series)
        }
        binding.rvSeries.adapter = seriesAdapter
        val layoutManager = GridLayoutManager(context, 2)

        binding.rvSeries.layoutManager = layoutManager
        binding.rvSeries.addOnScrollListener(object : PaginationRecyclerView(layoutManager) {
            override fun loadMoreItems() {
                page++
                Log.d("checkPageMoreItems", "$page ")
                isLoadDataOnProgress = true
                seriesViewModel.getPopularSeries(page)
            }

            override val isLastPage: Boolean
                get() = false

            override val isLoading: Boolean
                get() = isLoadDataOnProgress

        })
    }

    private fun intentToDetails(series: Series) {
        val intent = Intent(context, DetailsActivity::class.java)
        val parcelable = Series(
            series.backdropPath,
            series.firstAirDate,
            series.genreIds,
            series.id,
            series.name,
            series.originCountry,
            series.originalLanguage,
            series.originalName,
            series.overview,
            series.popularity,
            series.posterPath,
            series.voteAverage,
            series.voteCount
        )
        intent.putExtra(HomeFragment.EXTRA_DATA_MS, parcelable)
        intent.putExtra("id", series.id)
        intent.putExtra("category", "series")
        intent.putExtra(HomeFragment.CATEGORY, "series")
        startActivity(intent)
    }
}