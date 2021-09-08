package com.putrash.kumparantask.arch.ui.post.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.putrash.data.model.Post
import com.putrash.kumparantask.R
import com.putrash.kumparantask.arch.adapter.CommentAdapter
import com.putrash.kumparantask.arch.vm.MainViewModel
import com.putrash.kumparantask.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_post_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PostDetailFragment : BaseFragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val commentAdapter by lazy { CommentAdapter(requireContext()) {} }
    private lateinit var post: Post

    override fun setView(): Int {
        return R.layout.fragment_post_detail
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.toolbar_comments)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        post = viewModel.selectedPost
        Glide.with(requireContext()).load(post.img).into(iv_user)
        rv_comments.adapter = commentAdapter
        tv_post_title.isSelected = true
        tv_post_title.text = post.title
        tv_post_body.text = post.body
        tv_post_username.text = post.name
        tv_post_username.setOnClickListener {
            viewModel.selectedUserId = post.userId
            findNavController().navigate(R.id.action_postDetailFragment_to_userDetailFragment)
        }
    }

    override fun observeLiveData() {
        viewModel.comments.observe(viewLifecycleOwner, {
            commentAdapter.submitList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getComments(post.id)
    }
}