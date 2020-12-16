package com.example.ohouse.presentation.adapter.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder에 onBind()를 구현하도록 강제한다.
 */
abstract class BindingViewHolder<out T: ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: T = DataBindingUtil.bind(itemView)!!

    abstract fun onBind(position: Int)


}