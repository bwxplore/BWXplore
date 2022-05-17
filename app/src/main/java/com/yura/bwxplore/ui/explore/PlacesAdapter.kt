package com.yura.bwxplore.ui.explore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yura.bwxplore.data.firebase.entities.Location
import com.yura.bwxplore.databinding.ItemPlacesBinding

class PlacesAdapter(private var listLocation: ArrayList<Location>) :
    RecyclerView.Adapter<PlacesAdapter.ListViewHolder>() {

    private lateinit var binding: ItemPlacesBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        binding =
            ItemPlacesBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listLocation[position])
    }

    override fun getItemCount(): Int = listLocation.size

    class ListViewHolder(itemView: ItemPlacesBinding) : RecyclerView.ViewHolder(itemView.root) {
        var tvName: TextView = itemView.tvTitlePlace
        var imgPhoto: ImageView = itemView.imgPlace
        private val ivTick = itemView.tick
        fun bind(data: Location) {
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(imgPhoto)
            tvName.text = data.name

            ivTick.visibility = if (data.isChecked) View.VISIBLE else View.GONE
            itemView.setOnClickListener{
                data.isChecked = !data.isChecked
                ivTick.visibility = if (data.isChecked) View.VISIBLE else View.GONE
            }
        }
    }

    val selected: ArrayList<Location>
        get() {
            val select: ArrayList<Location> = ArrayList()
            for (i in 0 until listLocation.size) {
                if (listLocation[i].isChecked) {
                    select.add(listLocation[i])
                }
            }
            return select
        }

}