package com.example.unsplashproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.model.sitesearchmodel.Results

class SearchPhotoAdapter(private val context: Context?, private var listItem: List<Results>?=null, private var callback: (Results) -> Unit) :
    RecyclerView.Adapter<SearchPhotoAdapter.TopicItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.photo_item_design, parent, false)
        return TopicItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        val item= listItem!![position]
        Glide
            .with(context!!)
            .load(item.urls?.regular)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            callback.invoke(listItem!![position])
        }

    }

    override fun getItemCount(): Int {
        return listItem?.size ?: 0
    }

    class TopicItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.photo_iv)

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setupList(list: List<Results>?)
    {
        this.listItem= list

        Log.d("setupList", "%s %s".format(list?.size, list?.get(0)?.urls?.regular ))
        notifyDataSetChanged()
    }

}