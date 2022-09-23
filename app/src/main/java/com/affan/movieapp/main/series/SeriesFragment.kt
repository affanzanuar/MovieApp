package com.affan.movieapp.main.series

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.affan.movieapp.databinding.FragmentSeriesBinding
import com.affan.movieapp.model.series.Series
import com.affan.movieapp.main.details.DetailsActivity
import com.affan.movieapp.main.home.view.HomeFragment
import com.affan.movieapp.main.series.adapter.SeriesAdapter
import com.affan.movieapp.main.series.presenter.SeriesView
import com.affan.movieapp.main.series.viewmodel.SeriesViewModel

class SeriesFragment : Fragment(), SeriesView {

    private lateinit var binding: FragmentSeriesBinding
    private lateinit var seriesAdapter: SeriesAdapter

    private val seriesViewModel: SeriesViewModel by viewModels()

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
//        createPresenter()
        setSeriesAdapter()
//        seriesPresenter.getPopularSeries()
        seriesViewModel.series.observe(requireActivity()){
            it.series?.let { data->
                seriesAdapter.setData(data)
            }
        }
        seriesViewModel.errorMessage.observe(requireActivity()){
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSeriesAdapter() {
        seriesAdapter = SeriesAdapter { series: Series ->
            intentToDetails(series)
        }
        binding.rvSeries.adapter = seriesAdapter
        binding.rvSeries.layoutManager = GridLayoutManager(context, 2)
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
        intent.putExtra(HomeFragment.CATEGORY,"series")
        startActivity(intent)
    }

    override fun onReceiveSeries(series: List<SeriesData>) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetPopularSeries(series: List<Series?>) {
        seriesAdapter.setData(series)
    }

    override fun onFailGetPopularSeries(string: String) {
        TODO("Not yet implemented")
    }


}