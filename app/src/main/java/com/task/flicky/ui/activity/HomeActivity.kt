package com.task.flicky.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.task.flicky.R
import com.task.flicky.adapter.TabsAdapter
import com.task.flicky.databinding.ActivityHomeBinding
import com.task.flicky.domain.Constants
import com.task.flicky.domain.ViewModelFactory
import com.task.flicky.domain.core.BaseActivity
import com.task.flicky.ui.fragment.ListFragment
import com.task.flicky.utility.Utility
import com.task.flicky.viewModel.FeedsViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class HomeActivity : BaseActivity<ActivityHomeBinding>(),KodeinAware {

    override val layoutResId: Int
        get() = R.layout.activity_home

    override val kodein by closestKodein()
    private lateinit var viewModel: FeedsViewModel
    private val viewModelFactory: ViewModelFactory by instance()



    override fun onActivityReady(instance: Bundle?, binding: ActivityHomeBinding) {

        // make an instance of viewModel so that child can access
        viewModel = ViewModelProviders.of(this@HomeActivity, viewModelFactory)
            .get(FeedsViewModel::class.java)

        val adapter = TabsAdapter(supportFragmentManager)
        adapter.addFragment(
            ListFragment.getInstance(Constants.TAG_CATS),
            resources.getString(R.string.tab1)
        )
        adapter.addFragment(
            ListFragment.getInstance(Constants.TAG_DOGS),
            resources.getString(R.string.tab2)
        )
        adapter.addFragment(
            ListFragment.getInstance(Constants.TAG_BIRDS),
            resources.getString(R.string.tab3)
        )

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        Utility.showMessageIfNoNetwork(binding.container)

    }

    override fun getToolBar(binding: ActivityHomeBinding): Toolbar? {
        return null
    }


}
