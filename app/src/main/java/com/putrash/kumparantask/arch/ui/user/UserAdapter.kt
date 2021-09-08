package com.putrash.kumparantask.arch.ui.user

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.putrash.kumparantask.arch.ui.user.UserDetailFragment
import com.putrash.kumparantask.arch.ui.user.tab.UserAlbumFragment
import com.putrash.kumparantask.arch.ui.user.tab.UserPostFragment

class UserAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            UserPostFragment()
        } else {
            UserAlbumFragment()
        }
    }
}