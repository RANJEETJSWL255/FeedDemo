package sample.com.feeddemo.repositories

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sample.com.feeddemo.data.Feed
import sample.com.feeddemo.network.ApiClient
import sample.com.feeddemo.network.FeedService

object FeedRepository {
    private var feedService: FeedService = ApiClient.feedService()
    val mutableLiveData: MutableLiveData<Feed> = MutableLiveData()

    fun loadFeed() {
         feedService.getFeed().enqueue(object: Callback<Feed>{
             override fun onFailure(call: Call<Feed>, t: Throwable) {
             }

             override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                 mutableLiveData.postValue(response.body())
             }

         })
    }

    fun getInstance() : FeedRepository{
        return this
    }
}