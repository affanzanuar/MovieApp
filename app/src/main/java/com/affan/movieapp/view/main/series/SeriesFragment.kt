package com.affan.movieapp.view.main.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.affan.movieapp.databinding.FragmentSeriesBinding

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1"),
            SeriesData("TEST1", "DESC1")
        )

        val adapter = SeriesAdapter()
        binding.rvSeries.adapter = adapter
        binding.rvSeries.layoutManager = GridLayoutManager(context,2)
        adapter.setData(items)
    }
}