package com.affan.movieapp.presentation.main.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ItemTopMoviesBinding
import com.affan.movieapp.data.model.trending.Trending
import com.bumptech.glide.Glide

class TrendingAdapter (
    private val onClickToDetails : (data : Trending) -> Unit
) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>(){

    private val itemMoviesOrSeries = mutableListOf<Trending?>()

    inner class TrendingViewHolder (val binding: ItemTopMoviesBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind (item : Trending){
                Glide.with(binding.root)
                    .load(item.loadBackdrop())
                    .placeholder(R.drawable.ic_default_backdrop)
                    .into(binding.ivItemTopMovies)

                binding.tvTopMoviesTitle.text = item.title ?: item.name

                binding.root.setOnClickListener {
                    onClickToDetails(item)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            ItemTopMoviesBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        itemMoviesOrSeries[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return itemMoviesOrSeries.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataTrending (item : List<Trending?>){
        itemMoviesOrSeries.clear()
        for (i in 0..4){
            itemMoviesOrSeries.add(item[i])
        }
        notifyDataSetChanged()
    }
}