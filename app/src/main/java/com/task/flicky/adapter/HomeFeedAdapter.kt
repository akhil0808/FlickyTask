package com.task.flicky.adapter

import androidx.recyclerview.widget.DiffUtil
import com.task.flicky.R
import com.task.flicky.domain.core.BaseRecyclerAdapter
import com.task.flicky.domain.network.response.Feed



class HomeFeedAdapter : BaseRecyclerAdapter<Feed>(Callback()) {


    class Callback : DiffUtil.ItemCallback<Feed>() {

        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.dateTaken.equals(newItem.dateTaken, ignoreCase = true)
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.dateTaken == newItem.dateTaken &&
                    oldItem.media.imageUrl == newItem.media.imageUrl

        }

    }


    override fun getItemViewType(position: Int): Int {
            return R.layout.item_feed_home
    }


}