package com.example.unsplashproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.model.sitesearchusermodel.Results

class SearchUserAdapter(
    private var callback: (Results) -> Unit
) : PagingDataAdapter<Results, SearchUserAdapter.UserSearchItemViewHolder>(SearchUserDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item_design, parent, false)
        return UserSearchItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserSearchItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.txt.text = item?.name
        Glide
            .with(holder.img)
            .load(item?.profileImage?.large)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            callback.invoke(getItem(position)!!)
        }

        holder.txt.isSelected = true

    }

    class UserSearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.user_iv)
        val txt: TextView = itemView.findViewById(R.id.tv_user_name)

    }

    object SearchUserDiffUtil : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }

}