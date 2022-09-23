package com.affan.movieapp.main.account.myfavorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.ItemContainerFavoriteBinding
import com.affan.movieapp.model.MoviesOrSeries
import com.bumptech.glide.Glide

class FavoriteAdapter(
    private val onClickFavorite : (data : MoviesOrSeries) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val itemFavorite = arrayListOf<MoviesOrSeries>()

    inner class FavoriteViewHolder (private val binding: ItemContainerFavoriteBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind (position: Int){
                val item = itemFavorite[position]
                Glide.with(binding.root)
                    .load(item.moviesOrSeriesPoster)
                    .into(binding.ivPosterFavorite)
                binding.tvTitleFavorite.text = item.moviesOrSeriesTitle

                binding.root.setOnClickListener {
                    onClickFavorite(item)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(ItemContainerFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return itemFavorite.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataFavorite (data : List<MoviesOrSeries>){
        itemFavorite.clear()
        itemFavorite.addAll(data)
        notifyDataSetChanged()
    }
}