package com.putrash.kumparantask.arch.ui.post

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.putrash.data.model.Post
import com.putrash.kumparantask.R
import com.putrash.kumparantask.arch.adapter.PostAdapter
import com.putrash.kumparantask.arch.vm.MainViewModel
import com.putrash.kumparantask.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_post.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PostFragment : BaseFragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val postAdapter by lazy { PostAdapter(requireContext(), ::onItemClick, ::onUserClick) }

    override fun setView(): Int {
        return R.layout.fragment_post
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.toolbar_posts)
        rv_posts.adapter = postAdapter
        swipe_layout.setOnRefreshListener {
            viewModel.getPosts()
        }
        viewModel.getPosts()
    }

    override fun observeLiveData() {
        viewModel.posts.observe(viewLifecycleOwner, {
            swipe_layout.isRefreshing = false
            postAdapter.submitList(it)
        })
    }

    private fun onItemClick(post: Post) {
        viewModel.selectedPost = post
        findNavController().navigate(R.id.action_postFragment_to_postDetailFragment)
    }

    private fun onUserClick(post: Post) {
        viewModel.selectedPost = post
        viewModel.selectedUserId = post.userId
        findNavController().navigate(R.id.action_postFragment_to_userDetailFragment)
    }

}