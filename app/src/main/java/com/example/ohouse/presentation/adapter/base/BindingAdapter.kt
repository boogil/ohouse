package com.example.ohouse.presentation.adapter.base

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ohouse.data.data.Card
import com.example.ohouse.data.data.User
import com.example.ohouse.presentation.adapter.FavoriteCardAdapter
import com.example.ohouse.presentation.adapter.FavoriteUserAdapter
import com.example.ohouse.presentation.adapter.PhotoFeedAdapter

@BindingAdapter(value = ["items"])
fun setFavoriteCardAdapter(view: RecyclerView, items: ArrayList<Card>?) {
    items?.let {
        view.adapter?.run {
            (this as FavoriteCardAdapter).run {
                this.items = it
                this.notifyDataSetChanged()
            }
        } ?: run {
            view.adapter = FavoriteCardAdapter(view.context, items = it)
        }
    }
}

@BindingAdapter(value = ["items"])
fun setFavoriteUserAdapter(view: RecyclerView, items: ArrayList<User>?) {
    items?.let {
        view.adapter?.run {
            (this as FavoriteUserAdapter).run {
                this.items = it
                this.notifyDataSetChanged()
            }
        } ?: run {
            view.adapter = FavoriteUserAdapter(view.context, items = it)
        }
    }
}


@BindingAdapter(value = ["photoFeeds"])
fun setPhotoFeedAdapter(view: RecyclerView, items: ArrayList<Card>?) {
    items?.let {
        view.adapter?.run {
            (this as PhotoFeedAdapter).run {
                this.items = it
                this.notifyDataSetChanged()
            }
        } ?: run {
            view.adapter = PhotoFeedAdapter(view.context, items = it)
        }
    }
}