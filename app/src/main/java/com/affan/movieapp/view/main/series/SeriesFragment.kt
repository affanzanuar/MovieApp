package com.affan.movieapp.view.main.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.affan.movieapp.databinding.FragmentSeriesBinding
import com.affan.movieapp.view.main.series.adapter.SeriesAdapter
import com.affan.movieapp.view.main.series.presenter.SeriesPresenter
import com.affan.movieapp.view.main.series.presenter.SeriesView

class SeriesFragment : Fragment(), SeriesView {

    private lateinit var binding: FragmentSeriesBinding
    private lateinit var seriesAdapter: SeriesAdapter
    private lateinit var seriesPresenter: SeriesPresenter


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
        createPresenter()
        setSeriesAdapter()
        seriesPresenter.getMovies()
    }

    private fun setSeriesAdapter() {
        seriesAdapter = SeriesAdapter()
        binding.rvSeries.adapter = seriesAdapter
        binding.rvSeries.layoutManager = GridLayoutManager(context, 2)
    }

    private fun createPresenter() {
        seriesPresenter = SeriesPresenterImpl(this)
    }

    override fun onReceiveSeries(series: List<SeriesData>) {
        seriesAdapter.setData(series)
    }


}