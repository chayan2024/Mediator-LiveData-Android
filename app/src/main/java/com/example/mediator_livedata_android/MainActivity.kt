package com.example.mediator_livedata_android
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mediator_livedata_android.adapter.MainAdapter
import com.example.mediator_livedata_android.model.Comment
import com.example.mediator_livedata_android.model.Post
import com.example.mediator_livedata_android.model.User
import com.example.mediator_livedata_android.repository.MainRepository
import com.example.mediator_livedata_android.viewmodel.MainViewModel
import com.example.mediator_livedata_android.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MainViewModelFactory(MainRepository()) // Assuming MainRepository is your repository class

        // Initialize ViewModel and Adapter
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        adapter = MainAdapter()
        recyclerView = findViewById(R.id.recyclerView)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Load data
        viewModel.loadData()

        // Observe combined data and update RecyclerView adapter
        viewModel.combinedData.observe(this) { dataList ->
            val users = dataList.filterIsInstance<User>()
            val posts = dataList.filterIsInstance<Post>()
            Log.v("@@",""+posts.toString())
            val comments = dataList.filterIsInstance<Comment>()
            Log.v("@@",""+comments.toString())
            adapter.submitList(dataList)
        }


    }
}
