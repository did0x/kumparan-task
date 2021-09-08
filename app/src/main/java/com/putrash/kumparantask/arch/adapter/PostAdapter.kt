package com.putrash.kumparantask.arch.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putrash.data.model.Post
import com.putrash.kumparantask.R
import com.putrash.kumparantask.base.BaseListAdapter
import com.putrash.kumparantask.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_post_list.view.*

class PostAdapter(
    private val context: Context,
    private val onClickListener: (Post) -> Unit,
    private val onNameClickListener: (Post) -> Unit
) : BaseListAdapter<Post>(context, onClickListener, PostDiffCallback) {

    override fun setView(viewType: Int): Int {
        return R.layout.item_post_list
    }

    override fun itemViewHolder(
        context: Context,
        view: View,
        viewType: Int,
        onClickListener: (Post) -> Unit
    ): RecyclerView.ViewHolder {
        return PostViewHolder(view, onNameClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).onBind(context, getItem(position), onClickListener)
    }

    class PostViewHolder(itemView: View, private val onNameClickListener: (Post) -> Unit) :
        BaseViewHolder<Post>(itemView) {
        override fun onBind(context: Context, item: Post, onClickListener: (Post) -> Unit) {
            Glide.with(context).load(item.img).into(itemView.iv_user)
            itemView.tv_post_title.text = item.title
            itemView.tv_post_body.text = item.body
            itemView.tv_user_name.text =
                context.getString(R.string.post_user_text, item.name, item.company)
            itemView.tv_user_name.setOnClickListener {
                onNameClickListener(item)
            }
            itemView.setOnClickListener { onClickListener(item) }
        }
    }

    object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }
    }
}