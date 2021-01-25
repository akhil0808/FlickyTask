package com.task.flicky.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.task.flicky.domain.database.entity.FeedEntry
import com.task.flicky.domain.repository.FlickerRepository
import com.task.flicky.utility.lazyDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class FeedsViewModel(private val repository: FlickerRepository) : ViewModel(){


    /*
    *  getting feed data from data base by the tag. form flicky repository.
    * */

    fun getFeedByTagAsync(tag: String): Deferred<LiveData<FeedEntry>> {
        val feeds by lazyDeferred {
            repository.getFeedByTag(tag)
        }
        return feeds
    }


    /*
    * load data every time when user opens the app. this will hit the api and save the data into data base by tags*/
    fun loadFeedByTag(tag: String) {
        GlobalScope.launch {
            repository.loadFeeds(tag)
        }
    }



}