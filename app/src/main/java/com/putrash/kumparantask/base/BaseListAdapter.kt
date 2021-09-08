package com.putrash.kumparantask.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T>(
    private val context: Context,
    private val onClickListener: (T) -> Unit,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {

    @LayoutRes
    protected abstract fun setView(viewType: Int): Int

    protected abstract fun itemViewHolder(
        context: Context,
        view: View,
        viewType: Int,
        onClickListener: (T) -> Unit
    ): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(setView(viewType), parent, false)
        return itemViewHolder(context, view, viewType, onClickListener)
    }
}