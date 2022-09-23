package com.affan.movieapp.main.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ItemHorizontalContainerBinding
import com.affan.movieapp.model.series.Series
import com.bumptech.glide.Glide

class HomeSeriesAdapter (
    private val onClickToDetails : (data : Series) -> Unit
        ) : RecyclerView.Adapter<HomeSeriesAdapter.SeriesListAdapter> () {

    private val itemSeries = mutableListOf<Series?>()

    inner class SeriesListAdapter(val binding: ItemHorizontalContainerBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Series){
            Glide.with(binding.root)
                .load(item.loadPoster())
                .placeholder(R.drawable.ic_default_poster)
                .into(binding.ivMoviesOrSeries)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesListAdapter {
        return SeriesListAdapter(ItemHorizontalContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: SeriesListAdapter, position: Int) {
        itemSeries[position]?.let { holder.bind(it) }

        holder.binding.root.setOnClickListener {
            itemSeries[position]?.let { it1 -> onClickToDetails(it1) }
        }
    }

    override fun getItemCount(): Int {
        return itemSeries.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataSeries (item : List<Series?>){
        itemSeries.clear()
        itemSeries.addAll(item)
        notifyDataSetChanged()
    }
}