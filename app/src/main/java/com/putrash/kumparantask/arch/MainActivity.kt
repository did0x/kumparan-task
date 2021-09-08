package com.putrash.kumparantask.arch

import android.os.Bundle
import com.putrash.kumparantask.R
import com.putrash.kumparantask.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        return
    }
}