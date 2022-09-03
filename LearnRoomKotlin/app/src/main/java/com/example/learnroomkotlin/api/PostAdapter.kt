package com.example.learnroomkotlin.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnroomkotlin.R
import kotlinx.android.synthetic.main.item_posts.view.*

class PostAdapter(val postModel: MutableList<PostModel>): RecyclerView.Adapter<PostViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
        return PostViewModel(view)
    }

    override fun onBindViewHolder(holder: PostViewModel, position: Int) {
        return holder.bindView(postModel[position])
    }

    override fun getItemCount(): Int {
        return postModel.size
    }
}

class PostViewModel(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val txtTitle = itemView.txtTitle
    private val txtBody = itemView.txtBody

    fun bindView(postModel: PostModel) {
        txtTitle.text = postModel.title
        txtBody.text = postModel.body
    }
}
