package sample.com.feeddemo

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_feed.*
import sample.com.feeddemo.adapters.FeedRecyclerViewAdapter
import sample.com.feeddemo.data.Feed
import sample.com.feeddemo.viewmodels.FeedViewModel

class FeedActivity : AppCompatActivity() {
    private lateinit var recyclerViewFeed: RecyclerView
    private lateinit var pullToRefresh: SwipeRefreshLayout
    private var adapter: FeedRecyclerViewAdapter? = null
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        setSupportActionBar(toolbar)
        initView()
        val feedViewModel: FeedViewModel by viewModels()
        feedViewModel.getFeed().observe(this, Observer<Feed> {
            feed -> adapter?.setFeedItems(feed.feedList)
            toolbar.title = feed.title
            progressBar.visibility = View.GONE
            pullToRefresh.visibility = View.VISIBLE
        })
        pullToRefresh(feedViewModel)
    }

    private fun pullToRefresh(feedViewModel: FeedViewModel){
        pullToRefresh.setOnRefreshListener {  feedViewModel.getFeed().observe(this, Observer<Feed> {
                feed -> adapter?.setFeedItems(feed.feedList)
                toolbar.title = feed.title
                pullToRefresh.isRefreshing = false
        }) }
    }

    private fun initView(){
        recyclerViewFeed = findViewById(R.id.recyclerViewFeed)
        progressBar = findViewById(R.id.progressBar)
        pullToRefresh = findViewById(R.id.pullToRefresh)
        pullToRefresh.visibility = View.GONE
        recyclerViewFeed.layoutManager = LinearLayoutManager(this)
        adapter = FeedRecyclerViewAdapter(this)
        recyclerViewFeed.adapter = adapter
    }
}
