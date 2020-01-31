package sample.com.feeddemo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Feed (
    @Expose
    @SerializedName("rows")
    var feedList: List<FeedItem> = ArrayList<FeedItem>(),
    @Expose
    @SerializedName("title")
    var title: String? = null
)