package com.putrash.kumparantask.arch.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putrash.common.properUrl
import com.putrash.data.response.PhotoData
import com.putrash.kumparantask.R
import com.putrash.kumparantask.base.BaseListAdapter
import com.putrash.kumparantask.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_photo_list.view.*

class PhotoAdapter(
    private val context: Context,
    private val onClickListener: (PhotoData) -> Unit,
) : BaseListAdapter<PhotoData>(context, onClickListener, PhotoDiffCallback) {

    override fun setView(viewType: Int): Int {
        return R.layout.item_photo_list
    }

    override fun itemViewHolder(
        context: Context,
        view: View,
        viewType: Int,
        onClickListener: (PhotoData) -> Unit
    ): RecyclerView.ViewHolder {
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).onBind(context, getItem(position), onClickListener)
    }

    class PostViewHolder(itemView: View) : BaseViewHolder<PhotoData>(itemView) {
        override fun onBind(
            context: Context,
            item: PhotoData,
            onClickListener: (PhotoData) -> Unit
        ) {
            val url = item.thumbnailUrl.properUrl(context)
            Glide.with(context).load(url).into(itemView.iv_thumbnail)
            itemView.tv_photo_title.text = item.title
            itemView.setOnClickListener { onClickListener(item) }
        }
    }

    object PhotoDiffCallback : DiffUtil.ItemCallback<PhotoData>() {
        override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}