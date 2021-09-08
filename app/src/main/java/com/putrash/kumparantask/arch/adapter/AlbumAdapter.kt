package com.putrash.kumparantask.arch.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putrash.common.properUrl
import com.putrash.data.model.Album
import com.putrash.kumparantask.R
import com.putrash.kumparantask.base.BaseListAdapter
import com.putrash.kumparantask.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_album_list.view.*
import kotlinx.android.synthetic.main.item_album_list.view.iv_thumbnail

class AlbumAdapter(
    private val context: Context,
    private val onClickListener: (Album) -> Unit
) : BaseListAdapter<Album>(context, onClickListener, AlbumDiffCallback) {

    override fun setView(viewType: Int): Int {
        return R.layout.item_album_list
    }

    override fun itemViewHolder(
        context: Context,
        view: View,
        viewType: Int,
        onClickListener: (Album) -> Unit
    ): RecyclerView.ViewHolder {
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AlbumViewHolder).onBind(context, getItem(position), onClickListener)
    }

    class AlbumViewHolder(itemView: View) : BaseViewHolder<Album>(itemView) {
        override fun onBind(
            context: Context, item: Album,
            onClickListener: (Album) -> Unit
        ) {
            val url = item.thumbnailUrl.properUrl(context)
            Glide.with(context).load(url).into(itemView.iv_thumbnail)
            itemView.tv_album_post.text = context.getString(R.string.album_photos_text, item.photos)
            itemView.tv_album_title.text = item.title
            itemView.setOnClickListener { onClickListener(item) }
        }
    }

    object AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

    }
}