package com.example.projectoslo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdeltatwo.R
import com.example.projectoslo.cache.ArticleEntity
import com.example.projectoslo.models.Article

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

     private var articleList: List<ArticleEntity> = emptyList()

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val article = articleList[position]
        holder.itemView.findViewById<TextView>(R.id.author_text_view).text = article.author
        holder.itemView.findViewById<TextView>(R.id.post_title_tv).text = article.title
        holder.itemView.findViewById<TextView>(R.id.description_tv).text = article.description
    }

    fun setPosts(articles: List<ArticleEntity>){
        this.articleList = articles
        notifyItemRangeChanged(0, articles.size)
    }
}