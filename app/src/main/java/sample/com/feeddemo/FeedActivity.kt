package sample.com.feeddemo

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_feed.*
import org.jetbrains.annotations.TestOnly
import sample.com.feeddemo.adapters.FeedRecyclerViewAdapter
import sample.com.feeddemo.data.Feed
import sample.com.feeddemo.viewmodels.FeedViewModel

class FeedActivity : AppCompatActivity() {
    private lateinit var recyclerViewFeed: RecyclerView
    private lateinit var pullToRefresh: SwipeRefreshLayout
    private var adapter: FeedRecyclerViewAdapter? = null
    private lateinit var progressBar: ProgressBar
    private lateinit var observer: Observer<Feed>
    private lateinit var errorObserver: Observer<String>
    private lateinit var isLoadingObserver: Observer<Boolean>
    lateinit var feedViewModel: FeedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        setSupportActionBar(toolbar)
        initView()
        val feedViewModel: FeedViewModel by viewModels()
        this.feedViewModel = feedViewModel
        //Observer to updated the data based on response
        observer = Observer{
                feed -> adapter?.setFeedItems(feed.feedList)
            toolbar.title = feed.title
            progressBar.visibility = View.GONE
            pullToRefresh.visibility = View.VISIBLE
            pullToRefresh.isRefreshing = false
        }
        //Observer to display the error from network call
        errorObserver = Observer {
            message -> if(message != null && message != ""){Toast.makeText(this, message, Toast.LENGTH_SHORT).show()}
            progressBar.visibility = View.GONE
            pullToRefresh.isRefreshing = false
        }
        //Observer to display/hide the progress
        isLoadingObserver = Observer (){
            isLoading ->
            if(isLoading){
                progressBar.visibility = View.VISIBLE
                pullToRefresh.visibility = View.GONE
            }else{
                progressBar.visibility = View.GONE
                pullToRefresh.visibility = View.VISIBLE
            }
        }
        pullToRefresh(feedViewModel)
    }

    override fun onStart() {
        super.onStart()
        feedViewModel.isLoading().observe(this,isLoadingObserver)
        feedViewModel.getFeed().observe(this, observer)
        feedViewModel.getError().observe(this, errorObserver)
    }

    /**
     * Initiate new pull request to update data from network
     */
    private fun pullToRefresh(feedViewModel: FeedViewModel){
        pullToRefresh.setOnRefreshListener {  feedViewModel.loadFeed(false)}
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

    @TestOnly
    fun setViewModel(feedViewModel: FeedViewModel){
        this.feedViewModel = feedViewModel
    }
}
