package sample.com.feeddemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sample.com.feeddemo.data.Feed
import sample.com.feeddemo.repositories.FeedRepository

class FeedViewModel: ViewModel() {
    private val repository: FeedRepository = FeedRepository.getInstance()
    private val feedLiveData: MutableLiveData<Feed> = repository.mutableLiveData

    fun getFeed(): LiveData<Feed>{
        loadFeed()
        return feedLiveData
    }

    private fun loadFeed(){
        repository.loadFeed()
    }
}