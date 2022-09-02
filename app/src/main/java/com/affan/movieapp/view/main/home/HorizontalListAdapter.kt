package com.affan.movieapp.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.ItemHorizontalContainerBinding
import com.affan.movieapp.model.MoviesOrSeries
import com.bumptech.glide.Glide

class HorizontalListAdapter (
    private val item : ArrayList<MoviesOrSeries>,
    private val onClickToDetails : (data : MoviesOrSeries) -> Unit
) : RecyclerView.Adapter<HorizontalListAdapter.HorizontalGridViewHolder>()  {
    class HorizontalGridViewHolder (val binding: ItemHorizontalContainerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalGridViewHolder {
        return HorizontalGridViewHolder(ItemHorizontalContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalGridViewHolder, position: Int) {
        val item = item[position]

        Glide.with(holder.binding.root)
            .load(item.moviesOrSeriesImage)
            .into(holder.binding.ivMoviesOrSeries)

        holder.binding.root.setOnClickListener {
            onClickToDetails(item)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}