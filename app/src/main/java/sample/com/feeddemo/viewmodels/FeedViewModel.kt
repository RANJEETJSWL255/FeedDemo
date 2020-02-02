package sample.com.feeddemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import sample.com.feeddemo.data.Feed
import sample.com.feeddemo.repositories.FeedRepository

class FeedViewModel: ViewModel() {
    private val repository: FeedRepository = FeedRepository.getInstance()

    init {
        loadFeed(true)
    }

    fun getFeed(): LiveData<Feed>{
        return repository.mutableLiveData
    }

    fun getError(): LiveData<String>{
        return repository.errorMutableLiveData
    }

    fun loadFeed(isOnLaunch: Boolean){
        repository.loadFeed(isOnLaunch)
    }

    fun isLoading(): LiveData<Boolean>{
        return  repository.isLoadingMutableLiveData
    }
}