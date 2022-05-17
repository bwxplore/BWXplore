package com.yura.bwxplore.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yura.bwxplore.data.firebase.entities.Location
import com.yura.bwxplore.databinding.ItemPopularBinding

class PopularAdapter(private val listLocation: ArrayList<Location>) :
    RecyclerView.Adapter<PopularAdapter.ListViewHolder>() {

    private lateinit var binding: ItemPopularBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        binding =
            ItemPopularBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listLocation[position])
    }

    override fun getItemCount(): Int = listLocation.size

    class ListViewHolder(itemView: ItemPopularBinding) : RecyclerView.ViewHolder(itemView.root) {
        var tvName: TextView = itemView.tvPopular
        var imgPhoto: ImageView = itemView.ivPopular
        fun bind(data: Location) {
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(imgPhoto)
            tvName.text = data.name
        }
    }
}