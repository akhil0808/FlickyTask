package com.task.flicky.ui.fragment


import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.task.flicky.R
import com.task.flicky.adapter.HomeFeedAdapter
import com.task.flicky.databinding.FragmentListBinding
import com.task.flicky.domain.core.BaseFragment
import com.task.flicky.utility.getViewModel
import com.task.flicky.viewModel.FeedsViewModel
import kotlinx.coroutines.launch




const val TAG ="tag"
class ListFragment : BaseFragment<FragmentListBinding>() {

    override val layoutResId: Int
        get() = R.layout.fragment_list

    private lateinit var viewModel: FeedsViewModel
    private lateinit var mBinding:FragmentListBinding
    private val adapter = HomeFeedAdapter()

    override fun onFragmentReady(instanceState: Bundle?, binding: FragmentListBinding) {
        mBinding = binding

        // initializing viewModel from activity scope provide by view model provider
        activity?.let {
            viewModel = it.getViewModel()
        }

        val layoutManager = GridLayoutManager(activity!!,2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.isFocusable = false
        binding.recyclerView.adapter = adapter


        arguments?.let {
            val tag = it.getString(TAG)
            tag?.let {tag->
                viewModel.loadFeedByTag(tag)
                fillData(tag)
            }
        }

    }

    private fun fillData(tag:String)= launch {

        val feeds = viewModel.getFeedByTagAsync(tag).await()
        feeds.observe(this@ListFragment, Observer {
            if (it == null)return@Observer
            mBinding.progress.visibility = View.GONE
            adapter.submitList(it.feeds)
            Handler().post {
                mBinding.recyclerView.smoothScrollToPosition(0)
            }

        })

    }


    companion object {
        fun getInstance(tag: String): ListFragment {
            val bundle = Bundle()
            bundle.putString(TAG, tag)
            val fragment = ListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
