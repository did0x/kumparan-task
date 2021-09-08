package com.putrash.kumparantask.arch.ui.user.tab

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.putrash.data.model.Post
import com.putrash.kumparantask.R
import com.putrash.kumparantask.arch.adapter.PostAdapter
import com.putrash.kumparantask.arch.vm.MainViewModel
import com.putrash.kumparantask.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_post.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserPostFragment : BaseFragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val postAdapter by lazy { PostAdapter(requireContext(), ::onItemClick) {} }

    override fun setView(): Int {
        return R.layout.fragment_user_post
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        rv_posts.adapter = postAdapter
    }

    override fun observeLiveData() {
        viewModel.posts.observe(viewLifecycleOwner, {
            postAdapter.submitList(it)
        })
    }

    private fun onItemClick(post: Post) {
        viewModel.selectedPost = post
        findNavController().navigate(R.id.action_userDetailFragment_to_postDetailFragment)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPostsByUser()
    }
}