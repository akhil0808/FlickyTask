package com.task.flicky.domain.database.entity


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.task.flicky.domain.database.FeedConverter
import com.task.flicky.domain.network.response.Feed




@Entity(indices = arrayOf(Index(value = ["tag"], unique = true)))
data class FeedEntry(
    @PrimaryKey(autoGenerate = false)
    var link: String = "",
    var tag: String = "",
    @TypeConverters(FeedConverter::class)
    var feeds: List<Feed> = listOf()
)

