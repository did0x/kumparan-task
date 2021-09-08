package com.putrash.kumparantask.arch.ui.photo.detail

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.putrash.common.properUrl
import com.putrash.kumparantask.R
import com.putrash.kumparantask.arch.vm.MainViewModel
import com.putrash.kumparantask.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoDetailFragment : BaseFragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    override fun setView(): Int {
        return R.layout.fragment_photo_detail
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        toolbar.title = viewModel.selectedPhoto.title
        toolbar.setNavigationIcon(R.drawable.ic_close)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        val url = viewModel.selectedPhoto.url.properUrl(requireContext())
        Glide.with(requireContext()).load(url).into(iv_photo)
    }

    override fun observeLiveData() {
        return
    }

}