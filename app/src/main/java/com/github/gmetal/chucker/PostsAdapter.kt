package com.github.gmetal.chucker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter(
    private val context: Context,
    var data: List<Post> = emptyList()
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(inflater.inflate(R.layout.item_post, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = data[position]
        holder.postTitle.text = currentPost.title
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle: TextView = itemView.findViewById(R.id.postTitle)
    }
}