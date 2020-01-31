package sample.com.feeddemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sample.com.feeddemo.adapters.FeedRecyclerViewAdapter
import sample.com.feeddemo.data.Feed
import sample.com.feeddemo.viewmodels.FeedViewModel

class FeedActivity : AppCompatActivity() {
    private lateinit var recyclerViewFeed: RecyclerView
    private var adapter: FeedRecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        initView()
        val feedViewModel: FeedViewModel by viewModels()
        feedViewModel.getFeed().observe(this, Observer<Feed> {
            feed -> adapter?.setFeedItems(feed.feedList)
        })


    }

    private fun initView(){
        recyclerViewFeed = findViewById(R.id.recyclerViewFeed)
        recyclerViewFeed.layoutManager = LinearLayoutManager(this)
        adapter = FeedRecyclerViewAdapter(this)
        recyclerViewFeed.adapter = adapter
    }
}
