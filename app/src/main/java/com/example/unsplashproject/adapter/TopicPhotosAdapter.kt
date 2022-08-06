package com.example.unsplashproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.model.sitephotomodel.Photos
import com.example.unsplashproject.model.sitephotomodel.PreviewPhotos

class TopicPhotosAdapter(private var context: Context?, private var imgList: List<PreviewPhotos>?=null, private var callback: ((PreviewPhotos) -> Unit)?) :
    RecyclerView.Adapter<TopicPhotosAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.photo_item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imgList!![position]
        Glide
            .with(context!!)
            .load(item.urls?.regular)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            callback!!.invoke(imgList!![position])
        }
    }

    override fun getItemCount(): Int {
        return imgList?.size ?: 0
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.photo_iv)
    }
    fun setupList(list :List<PreviewPhotos>?)
    {
        this.imgList=list
        notifyDataSetChanged()
    }
}