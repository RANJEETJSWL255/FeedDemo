package sample.com.feeddemo.network

import retrofit2.Call
import retrofit2.http.GET
import sample.com.feeddemo.data.Feed

interface FeedService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFeed(): Call<Feed>
}