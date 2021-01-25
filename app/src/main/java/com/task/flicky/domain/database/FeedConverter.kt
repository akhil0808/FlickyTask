package com.task.flicky.domain.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.task.flicky.domain.network.response.Feed
import com.task.flicky.utility.fromJson
import com.task.flicky.utility.json


class FeedConverter {


    @TypeConverter
    fun converToString(list: List<Feed>?): String {
        list ?: return ""
        return list.json()
    }

    @TypeConverter
    fun convertToFeeds(value: String?): List<Feed> {
        if (value.isNullOrEmpty()) {
            return arrayListOf()
        }
        return Gson().fromJson<ArrayList<Feed>>(value)
    }


}
