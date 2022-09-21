package com.affan.movieapp.view.main.series.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.CardLayoutBinding
import com.affan.movieapp.model.series.Series
import com.bumptech.glide.Glide

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private val series = mutableListOf<Series?>()

    inner class SeriesViewHolder(
        private val binding: CardLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(seriesData: Series) {
            Glide.with(binding.root)
                .load(seriesData.loadPoster())
                .into(binding.ivPoster)
            binding.tvMovieTitle.text = seriesData.name
            binding.tvDescription.text = seriesData.voteAverage.toString() + "/10"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder(
            CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        series[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return series.size
    }

    fun setData(data: List<Series?>) {
        series.clear()
        series.addAll(data)
        notifyDataSetChanged()
    }

}

