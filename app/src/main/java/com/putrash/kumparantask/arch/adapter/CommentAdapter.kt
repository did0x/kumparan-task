package com.putrash.kumparantask.arch.adapter

import android.content.Context
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.putrash.data.response.CommentData
import com.putrash.kumparantask.R
import com.putrash.kumparantask.base.BaseListAdapter
import com.putrash.kumparantask.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_comment_list.view.*

class CommentAdapter(
    private val context: Context,
    private val onClickListener: (CommentData) -> Unit
) : BaseListAdapter<CommentData>(context, onClickListener, CommentDiffCallback) {

    override fun setView(viewType: Int): Int {
        return R.layout.item_comment_list
    }

    override fun itemViewHolder(
        context: Context,
        view: View,
        viewType: Int,
        onClickListener: (CommentData) -> Unit
    ): RecyclerView.ViewHolder {
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommentViewHolder).onBind(context, getItem(position), onClickListener)
    }

    class CommentViewHolder(itemView: View) : BaseViewHolder<CommentData>(itemView) {
        override fun onBind(
            context: Context, item: CommentData,
            onClickListener: (CommentData) -> Unit
        ) {
            itemView.tv_comment_body.text = HtmlCompat.fromHtml(
                context.getString(R.string.comment_text, item.email, item.body),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            itemView.setOnClickListener { onClickListener(item) }
        }
    }

    object CommentDiffCallback : DiffUtil.ItemCallback<CommentData>() {
        override fun areItemsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
            return oldItem.id == newItem.id
        }

    }
}