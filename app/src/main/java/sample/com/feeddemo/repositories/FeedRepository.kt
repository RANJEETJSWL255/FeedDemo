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
    val errorMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun loadFeed(isOnLaunch: Boolean) {
        if(isOnLaunch) {
            isLoadingMutableLiveData.postValue(true)
        }
         feedService.getFeed().enqueue(object: Callback<Feed>{
             override fun onFailure(call: Call<Feed>, t: Throwable) {
                 errorMutableLiveData.postValue(t.localizedMessage)
                 if(isLoadingMutableLiveData.value == true) {
                     isLoadingMutableLiveData.postValue(false)
                 }
             }

             override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                 mutableLiveData.postValue(response.body())
                 errorMutableLiveData.postValue("")
                 if(isLoadingMutableLiveData.value == true) {
                     isLoadingMutableLiveData.postValue(false)
                 }
             }

         })
    }

    fun getInstance() : FeedRepository{
        return this
    }
}