package com.task.flicky.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.task.flicky.R
import com.task.flicky.databinding.ActivityDetailBinding
import com.task.flicky.domain.core.BaseActivity
import com.task.flicky.domain.glide.ImageLoader
import com.task.flicky.utility.Utility


const val TITLE ="title"
const val IMAGE = "image"
const val DATE ="date"
class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_detail

    override fun getToolBar(binding: ActivityDetailBinding): Toolbar? {
          return null
     }

    override fun onActivityReady(instance: Bundle?, binding: ActivityDetailBinding) {
             intent.extras?.let {bundle->
                 val imageUrl = bundle.getString(IMAGE)
                 imageUrl?.let {
                     ImageLoader.loadLargeImage(binding.image, it)
                 }
                 binding.title.text = bundle.getString(TITLE)
                 binding.date.text = Utility.getFormattedDate(bundle.getString(DATE))
                 Utility.showMessageIfNoNetwork(binding.container)

             }

    }

}
