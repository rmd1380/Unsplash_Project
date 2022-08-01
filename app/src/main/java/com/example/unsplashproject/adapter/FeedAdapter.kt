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

class FeedAdapter(private var context: Context?, private var imgList: List<PhotoResponse>?=null, private var callback: ((PhotoResponse) -> Unit)?) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.feed_item_design, parent, false)
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
        val img: ImageView = itemView.findViewById(R.id.feed_iv)
    }
    fun setupList(list :List<PhotoResponse>?)
    {
        this.imgList=list
        notifyDataSetChanged()
    }
}