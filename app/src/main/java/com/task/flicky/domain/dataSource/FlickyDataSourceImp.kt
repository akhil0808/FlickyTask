package com.task.flicky.domain.dataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.task.flicky.domain.database.entity.FeedEntry
import com.task.flicky.domain.network.FlickyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber



class FlickyDataSourceImp(private val flickyService: FlickyService) :FlickyDataSource{


    private val _downloadedFeeds = MediatorLiveData<FeedEntry>()
    override val downloadedFeeds: LiveData<FeedEntry>
        get() = _downloadedFeeds

    override suspend fun fetchFeedsData(tag:String) {
        withContext(Dispatchers.IO) {
            try {
                val fetchedData = flickyService.getFeedsAsync(tag, 1, 100, "json").await()

                val feedEntry = FeedEntry(tag = tag)
                feedEntry.feeds = fetchedData.feeds
                 fetchedData.feeds.forEach {
                     feedEntry.link = it.link
                 }
                _downloadedFeeds.postValue(feedEntry)
            } catch (e: Exception) {
                Timber.d(e.message)
            }
        }
    }
}