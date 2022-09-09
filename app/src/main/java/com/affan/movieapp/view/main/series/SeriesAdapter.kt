package com.affan.movieapp.view.main.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.CardLayoutBinding
import com.bumptech.glide.Glide

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private val series = arrayListOf<SeriesData>()

    inner class SeriesViewHolder(
        private val binding: CardLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(seriesData: SeriesData) {
            Glide.with(binding.root)
                .load(seriesData.seriesPoster)
                .into(binding.ivPoster)
            binding.tvMovieTitle.text = seriesData.seriesTitle
            binding.tvDescription.text = seriesData.seriesDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder(
            CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(series[position])
    }

    override fun getItemCount(): Int {
        return series.size
    }

    fun setData(data: List<SeriesData>) {
        series.clear()
        series.addAll(data)
        notifyDataSetChanged()
    }

}

