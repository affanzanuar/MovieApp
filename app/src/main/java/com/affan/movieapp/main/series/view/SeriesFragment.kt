package com.affan.movieapp.main.series.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.FragmentSeriesBinding
import com.affan.movieapp.di.ViewModelFactory
import com.affan.movieapp.main.details.DetailsActivity
import com.affan.movieapp.main.home.view.HomeFragment
import com.affan.movieapp.main.series.adapter.SeriesAdapter
import com.affan.movieapp.main.series.viewmodel.SeriesViewModel
import com.affan.movieapp.model.series.Series

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    private lateinit var seriesAdapter: SeriesAdapter

    private lateinit var mLayoutManager : LinearLayoutManager

    private val viewModel: SeriesViewModel by activityViewModels(
        factoryProducer = {
            ViewModelFactory.getInstance(requireContext())
        }
    )

    private var isLoading = false

    private var isLastPage = false

    private var isScrolling = false

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
        viewModel.page = 1
        viewModel.getPopularSeries()
    }

    private fun setSeriesAdapter() {
        binding.rvSeries.setHasFixedSize(true)
        seriesAdapter = SeriesAdapter { series: Series ->
            intentToDetails(series)
        }
        binding.rvSeries.adapter = seriesAdapter
        binding.rvSeries.layoutManager = mLayoutManager
        binding.rvSeries.addOnScrollListener(this.scrollListener)
    }

    private var scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val mChildCount = layoutManager.childCount
            val mItemCount = layoutManager.itemCount
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isLastItem = firstVisibleItemPosition + mChildCount >= mItemCount
            val isNotBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = mItemCount >= 20
            val shouldPaginate = isNotLoadingAndNotLastPage && isLastItem &&
                    isNotBeginning && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate){
                viewModel.getPopularSeries()
                isScrolling = false
            }
            Log.e("why SeriesFragment", mItemCount.toString())
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }

    private fun getObserve(){
        viewModel.series.observe(requireActivity()) { data ->
            binding.skSeriesFragment.visibility = View.GONE
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