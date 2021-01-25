package com.task.flicky.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.widget.Toolbar
import com.task.flicky.R
import com.task.flicky.databinding.ActivitySplashBinding
import com.task.flicky.domain.core.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_splash

    override fun getToolBar(binding: ActivitySplashBinding): Toolbar? {
           return null
    }


    override fun onActivityReady(instance: Bundle?, binding: ActivitySplashBinding) {
          Handler().postDelayed({
              startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
              finish()
          },500)

    }


}
