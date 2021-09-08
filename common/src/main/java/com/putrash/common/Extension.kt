package com.putrash.common

import android.content.Context
import android.webkit.WebSettings
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

fun String.format(): String {
    return this.replace("\n", ". ");
}

fun String.properUrl(context: Context): GlideUrl {
    return GlideUrl(
        this,
        LazyHeaders.Builder()
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context)).build()
    )
}