package com.task.flicky.domain.dataSource

import androidx.lifecycle.LiveData
import com.task.flicky.domain.database.entity.FeedEntry



interface FlickyDataSource {

    val downloadedFeeds: LiveData<FeedEntry>
    suspend fun fetchFeedsData(tag:String)


}