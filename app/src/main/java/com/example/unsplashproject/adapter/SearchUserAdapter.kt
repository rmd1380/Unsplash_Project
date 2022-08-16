package com.example.unsplashproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.model.sitesearchphotomodel.Results

class SearchUserAdapter(private val context: Context?, private var listItem: List<com.example.unsplashproject.model.sitesearchusermodel.Results>?=null, private var callback: (com.example.unsplashproject.model.sitesearchusermodel.Results) -> Unit) :
    RecyclerView.Adapter<SearchUserAdapter.TopicItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_item_design, parent, false)
        return TopicItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        val item= listItem!![position]
        holder.txt.text=item.name
        Glide
            .with(context!!)
            .load(item.profileImage?.large)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            callback.invoke(listItem!![position])
        }

        holder.txt.isSelected = true

    }

    override fun getItemCount(): Int {
        return listItem?.size ?: 0
    }

    class TopicItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.user_iv)
        val txt:TextView=itemView.findViewById(R.id.tv_user_name)

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setupList(list: List<com.example.unsplashproject.model.sitesearchusermodel.Results>?)
    {
        this.listItem= list
        notifyDataSetChanged()
    }

}