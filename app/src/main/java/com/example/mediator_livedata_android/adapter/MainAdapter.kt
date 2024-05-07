package com.example.mediator_livedata_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mediator_livedata_android.R
import com.example.mediator_livedata_android.model.User
import com.example.mediator_livedata_android.model.Post
import com.example.mediator_livedata_android.model.Comment

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: List<Any> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.content_main, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        when (holder) {
            is MainViewHolder -> holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun submitList(dataList: List<Any>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    // ViewHolder class for your RecyclerView item
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val postTitleTextView: TextView = itemView.findViewById(R.id.postTitleTextView)
        private val commentBodyTextView: TextView = itemView.findViewById(R.id.commentBodyTextView)

        fun bind(item: Any) {
            when (item) {
                is User -> {
                    // Bind user data
                    userNameTextView.text = item.name
                    postTitleTextView.visibility = View.GONE
                    commentBodyTextView.visibility = View.GONE
                }
                is Post -> {
                    // Bind post data
                    postTitleTextView.text = item.title
                    userNameTextView.visibility = View.GONE
                    commentBodyTextView.visibility = View.GONE
                }
                is Comment -> {
                    // Bind comment data
                    commentBodyTextView.text = item.body
                    userNameTextView.visibility = View.GONE
                    postTitleTextView.visibility = View.GONE
                }
            }
        }
    }
}
