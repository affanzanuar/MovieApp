package com.affan.movieapp.main.account.myfavorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.ItemContainerFavoriteBinding
import com.affan.movieapp.data.local.room.FavoriteMovies
import com.bumptech.glide.Glide

class FavoriteAdapter(
    private val onClickFavorite : (data : FavoriteMovies) -> Unit,
    private val onClickDelete : (data : FavoriteMovies) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val itemFavorite = arrayListOf<FavoriteMovies>()

    inner class FavoriteViewHolder (private val binding: ItemContainerFavoriteBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind (position: Int){
                val item = itemFavorite[position]
                Glide.with(binding.root)
                    .load(BASE_URL+item.poster)
                    .into(binding.ivPosterFavorite)

                binding.tvTitleFavorite.text = item.name ?: item.title

                binding.root.setOnClickListener {
                    onClickFavorite(item)
                }

                binding.ivDeleteFavorite.setOnClickListener {
                    onClickDelete(item)
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
    fun setDataFavorite (data : List<FavoriteMovies>){
        itemFavorite.clear()
        itemFavorite.addAll(data)
        notifyDataSetChanged()
    }

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}