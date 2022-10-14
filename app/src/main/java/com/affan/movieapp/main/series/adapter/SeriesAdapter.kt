package com.affan.movieapp.main.series.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.CardLayoutBinding
import com.affan.movieapp.data.model.series.Series
import com.bumptech.glide.Glide

class SeriesAdapter(
    private val onClickToDetails: (data: Series) -> Unit
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private val itemSeries = mutableListOf<Series?>()

    inner class SeriesViewHolder(
        val binding: CardLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(seriesData: Series) {
            Glide.with(binding.root)
                .load(seriesData.loadPoster())
                .into(binding.ivPoster)
            binding.tvTitle.text = seriesData.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        itemSeries[position]?.let { holder.bind(it) }

        holder.binding.root.setOnClickListener {
            itemSeries[position]?.let { it1 -> onClickToDetails(it1) }
        }
    }

    override fun getItemCount(): Int {
        return itemSeries.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(item: List<Series?>) {
        itemSeries.addAll(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData (){
        itemSeries.clear()
        notifyDataSetChanged()
    }

}

