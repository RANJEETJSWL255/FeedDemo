package sample.com.feeddemo.network

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient{
    private const val baseUrl: String = "https://dl.dropboxusercontent.com/"
    private var feedService: FeedService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        feedService = retrofit.create(FeedService::class.java)
    }

    fun feedService(): FeedService {
        return feedService
    }

}