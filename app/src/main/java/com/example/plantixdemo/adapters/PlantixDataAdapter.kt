package com.example.plantixdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantixdemo.R
import com.example.plantixdemo.databinding.ListSingleLayoutBinding
import com.example.plantixdemo.models.PlantixResponseModel
import com.example.plantixdemo.models.Row

class PlantixDataAdapter(private val context: Context, private val list: List<Row>) : RecyclerView.Adapter<PlantixDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_single_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val views = holder.binding
        val currentItem = list[position]

        views.title.text = currentItem.title

        Glide.with(context)
            .load(currentItem.imageHref)
            .into(views.imageData)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListSingleLayoutBinding.bind(itemView)
    }
}