package sample.com.feeddemo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeedItem (
    @Expose
    @SerializedName("title")
    var title: String? = null,
    @Expose
    @SerializedName("description")
    var description: String? = null,
    @Expose
    @SerializedName("imageHref")
    var imageHref: String? = null
)