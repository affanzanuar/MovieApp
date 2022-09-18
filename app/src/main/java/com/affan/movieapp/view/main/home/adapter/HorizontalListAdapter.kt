package com.affan.movieapp.view.main.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ItemHorizontalContainerBinding
import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.model.movie.Movie
import com.bumptech.glide.Glide

class HorizontalListAdapter(
    private val onClickToDetails : (data : Movie) -> Unit
) : RecyclerView.Adapter<HorizontalListAdapter.HorizontalGridViewHolder>() {

    private val itemMovie = mutableListOf<Movie?>()

    inner class HorizontalGridViewHolder (val binding: ItemHorizontalContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Movie){
            Glide.with(binding.root)
                .load(item.loadPoster())
                .placeholder(R.drawable.ic_default_horizontal)
                .into(binding.ivMoviesOrSeries)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalGridViewHolder {
        return HorizontalGridViewHolder(ItemHorizontalContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalGridViewHolder, position: Int) {
        itemMovie[position]?.let { holder.bind(it) }

        holder.binding.root.setOnClickListener {
            itemMovie[position]?.let { it1 -> onClickToDetails(it1) }
        }
    }

    override fun getItemCount(): Int {
        return itemMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData (item : List<Movie?>){
        Log.d("Main Adapter setData", item.toString())
        itemMovie.clear()
        itemMovie.addAll(item)
        notifyDataSetChanged()
    }
}