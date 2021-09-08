package com.putrash.kumparantask.arch.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.putrash.common.Constants
import com.putrash.kumparantask.R
import com.putrash.kumparantask.arch.vm.MainViewModel
import com.putrash.kumparantask.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserDetailFragment : BaseFragment() {
    private val viewModel: MainViewModel by sharedViewModel()
    private val titles = arrayOf("Posts", "Albums")

    override fun setView(): Int {
        return R.layout.fragment_user_detail
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.toolbar_profile)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        viewpager.adapter = UserAdapter(this)
        TabLayoutMediator(tab_layout, viewpager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun observeLiveData() {
        viewModel.user.observe(viewLifecycleOwner, { user ->
            Glide.with(requireContext()).load(user.img).into(iv_user)
            tv_user_name.text = user.name
            tv_user_company.text = getString(R.string.user_company_text, user.company)
            tv_user_address.text = getString(
                R.string.user_address_text,
                user.address.street,
                user.address.suite,
                user.address.city
            )
            tv_user_website.text = HtmlCompat.fromHtml(
                getString(R.string.user_link_text, user.website),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            tv_user_website.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://${user.website}"))
                startActivity(intent)
            }
            tv_user_email.text = HtmlCompat.fromHtml(
                getString(R.string.user_link_text, user.email),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            tv_user_email.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(user.email)) // recipients
                    putExtra(Intent.EXTRA_SUBJECT, Constants.EMAIL_SUBJECT)
                    putExtra(Intent.EXTRA_TEXT, Constants.EMAIL_TEXT)
                }
                startActivity(intent)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUser()
    }
}