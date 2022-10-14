package com.affan.movieapp.main.series.view

import android.content.Intent
import android.os.Bundle
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
import com.affan.movieapp.main.paginate.PaginationRecyclerView
import com.affan.movieapp.main.series.adapter.SeriesAdapter
import com.affan.movieapp.main.series.viewmodel.SeriesViewModel
import com.affan.movieapp.model.series.Series

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    private lateinit var seriesAdapter: SeriesAdapter

    private lateinit var mLayoutManager : GridLayoutManager

    private val viewModel: SeriesViewModel by activityViewModels(
        factoryProducer = {
            ViewModelFactory.getInstance(requireContext())
        }
    )

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
        mLayoutManager = GridLayoutManager(context, 2)
        setSeriesAdapter()
        getObserve()
        seriesAdapter.clearData()
        viewModel.pageSeries = 1
        viewModel.getPopularSeries()
    }

    private fun setSeriesAdapter() {
        binding.rvSeries.setHasFixedSize(true)
        seriesAdapter = SeriesAdapter { series: Series ->
            intentToDetails(series)
        }
        binding.rvSeries.adapter = seriesAdapter
        binding.rvSeries.layoutManager = mLayoutManager

        binding.rvSeries.addOnScrollListener(
            object : PaginationRecyclerView({ viewModel.getPopularSeries() }) {})
    }

    private fun getObserve(){
        viewModel.isLoading.observe(requireActivity()){ isLoading ->
            if (isLoading){
                binding.skSeriesFragment.visibility = View.VISIBLE
            } else {
                binding.skSeriesFragment.visibility = View.GONE
            }
        }
        viewModel.series.observe(requireActivity()) { data ->
            seriesAdapter.addAll(data!!)
        }
        viewModel.errorMessage.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun intentToDetails(series: Series) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(HomeFragment.ID, series.id)
        intent.putExtra(HomeFragment.CATEGORY, "series")
        startActivity(intent)
    }
}