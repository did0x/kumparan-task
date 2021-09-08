package com.putrash.kumparantask.arch.ui.photo

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.putrash.data.response.PhotoData
import com.putrash.kumparantask.R
import com.putrash.kumparantask.arch.adapter.PhotoAdapter
import com.putrash.kumparantask.arch.vm.MainViewModel
import com.putrash.kumparantask.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.fragment_photo.toolbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoFragment : BaseFragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val photoAdapter by lazy { PhotoAdapter(requireContext(), ::onItemClick) }

    override fun setView(): Int {
        return R.layout.fragment_photo
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.toolbar_photos)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        rv_photos.adapter = photoAdapter
    }

    override fun observeLiveData() {
        viewModel.photos.observe(viewLifecycleOwner, {
            photoAdapter.submitList(it)
        })
    }

    private fun onItemClick(photoData: PhotoData) {
        viewModel.selectedPhoto = photoData
        findNavController().navigate(R.id.action_photoFragment_to_photoDetailFragment)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPhotosByAlbum()
    }
}