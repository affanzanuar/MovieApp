package com.affan.movieapp.view.main.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ItemHorizontalContainerBinding
import com.affan.movieapp.model.comingsoon.ComingSoon
import com.bumptech.glide.Glide

class ComingSoonAdapter(
    private val onClickToDetails : (data : ComingSoon) -> Unit
) : RecyclerView.Adapter<ComingSoonAdapter.ComingSoonViewHolder> () {

    private val itemComingSoon = mutableListOf<ComingSoon?>()

    inner class ComingSoonViewHolder (val binding: ItemHorizontalContainerBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind(item : ComingSoon){
            Glide.with(binding.root)
                .load(item.loadPoster())
                .placeholder(R.drawable.ic_default_poster)
                .into(binding.ivMoviesOrSeries)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonViewHolder {
        return ComingSoonViewHolder(ItemHorizontalContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ComingSoonViewHolder, position: Int) {
        itemComingSoon[position]?.let { holder.bind(it) }

        holder.binding.root.setOnClickListener {
            itemComingSoon[position]?.let { it1 -> onClickToDetails(it1) }
        }
    }

    override fun getItemCount(): Int {
        return itemComingSoon.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataComingSoon(data : List<ComingSoon?>){
        itemComingSoon.clear()
        itemComingSoon.addAll(data)
        notifyDataSetChanged()
        Log.d("CS Adapter", data.toString())
    }
}