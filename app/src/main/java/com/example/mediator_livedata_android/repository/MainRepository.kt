package com.example.mediator_livedata_android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mediator_livedata_android.model.Comment
import com.example.mediator_livedata_android.model.Post
import com.example.mediator_livedata_android.model.User

class MainRepository {

    private val usersData = listOf(
        User(1, "User 1", "user1@example.com"),
        User(2, "User 2", "user2@example.com"),
        User(3, "User 3", "user3@example.com")
    )

    private val postsData = listOf(
        Post(1, 1, "Post 1", "Body of post 1"),
        Post(2, 2, "Post 2", "Body of post 2"),
        Post(3, 3, "Post 3", "Body of post 3")
    )

    private val commentsData = listOf(
        Comment(1, 1, "Comment 1", "commenter1@example.com", "Body of comment 1"),
        Comment(2, 2, "Comment 2", "commenter2@example.com", "Body of comment 2"),
        Comment(3, 3, "Comment 3", "commenter3@example.com", "Body of comment 3")
    )

    fun getUsers(): LiveData<List<User>> {
        val usersLiveData = MutableLiveData<List<User>>()
        usersLiveData.value = usersData
        return usersLiveData
    }

    fun getPosts(): LiveData<List<Post>> {
        val postsLiveData = MutableLiveData<List<Post>>()
        postsLiveData.value = postsData
        return postsLiveData
    }

    fun getComments(): LiveData<List<Comment>> {
        val commentsLiveData = MutableLiveData<List<Comment>>()
        commentsLiveData.value = commentsData
        return commentsLiveData
    }
}
