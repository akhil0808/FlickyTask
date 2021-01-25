package com.task.flicky.domain.repository

import androidx.lifecycle.LiveData
import com.task.flicky.domain.database.entity.FeedEntry



interface FlickerRepository {

    suspend fun loadFeeds(tag:String)
    suspend fun getFeedByTag(tag: String): LiveData<FeedEntry>

}