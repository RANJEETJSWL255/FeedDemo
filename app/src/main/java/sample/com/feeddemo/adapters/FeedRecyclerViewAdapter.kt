package sample.com.feeddemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sample.com.feeddemo.R
import sample.com.feeddemo.data.FeedItem

class FeedRecyclerViewAdapter(context: Context): RecyclerView.Adapter<FeedRecyclerViewAdapter.FeedViewHolder>() {
    private val feedItems: ArrayList<FeedItem> = ArrayList<FeedItem>()
    private var context: Context

    init {
        this.context = context
    }

    fun setFeedItems(feedItems: List<FeedItem>){
        this.feedItems.clear()
        this.feedItems.addAll(feedItems)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.feed_list_item, parent, false)
        return FeedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedItems.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        if (feedItems[position].imageHref != null) {
            holder.imageViewRef.visibility = View.VISIBLE
            Glide.with(context)
                .load(feedItems[position].imageHref)
                .into(holder.imageViewRef)
        } else {
            holder.imageViewRef.visibility = View.GONE
        }
        holder.textViewTitle.text = feedItems[position].title
        holder.textViewDescription.text = feedItems[position].description
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val imageViewRef: ImageView = itemView.findViewById(R.id.imageViewRef)
    }
}