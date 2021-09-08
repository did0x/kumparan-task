package com.putrash.kumparantask.arch.ui.user.tab

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.putrash.data.model.Album
import com.putrash.kumparantask.R
import com.putrash.kumparantask.arch.adapter.AlbumAdapter
import com.putrash.kumparantask.arch.vm.MainViewModel
import com.putrash.kumparantask.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_album.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserAlbumFragment : BaseFragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val albumAdapter by lazy { AlbumAdapter(requireContext(), ::onItemClick) }

    override fun setView(): Int {
        return R.layout.fragment_user_album
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        rv_albums.adapter = albumAdapter
    }

    override fun observeLiveData() {
        viewModel.albums.observe(viewLifecycleOwner, {
            albumAdapter.submitList(it)
        })
    }

    private fun onItemClick(album: Album) {
        viewModel.selectedAlbum = album
        findNavController().navigate(R.id.action_userDetailFragment_to_photoFragment)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAlbumsByUser()
    }

}