package com.example.mediator_livedata_android.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediator_livedata_android.model.Comment
import com.example.mediator_livedata_android.model.Post
import com.example.mediator_livedata_android.model.User
import com.example.mediator_livedata_android.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val userData = MutableLiveData<User>()
    val postData = MutableLiveData<List<Post>>()
    val commentData = MutableLiveData<List<Comment>>()

    val combinedData = MediatorLiveData<List<Any>>()

    init {
        combinedData.addSource(userData) { combineData() }
        combinedData.addSource(postData) { combineData() }
        combinedData.addSource(commentData) { combineData() }
    }

    fun loadData() {
        viewModelScope.launch {
            userData.value = repository.getUsers().value?.firstOrNull() // First user from the list
            postData.value = repository.getPosts().value ?: emptyList()
            commentData.value = repository.getComments().value ?: emptyList()
        }
    }

    private fun combineData() {
        val combinedList = mutableListOf<Any>()
        userData.value?.let { user ->
            combinedList.add(user)
        }
        combinedList.addAll(postData.value ?: emptyList())
        combinedList.addAll(commentData.value ?: emptyList())
        combinedData.value = combinedList
    }
}
