package com.example.ohouse.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ohouse.R
import com.example.ohouse.data.data.Card
import com.example.ohouse.data.data.User
import com.example.ohouse.databinding.ItemCardBinding
import com.example.ohouse.databinding.ItemUserBinding
import com.example.ohouse.presentation.adapter.base.BindingViewHolder

class FavoriteUserAdapter(
    val context: Context,
    var items: ArrayList<User> = arrayListOf()
) : RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ViewDataBinding> {
        return CardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = items.size

    inner class CardViewHolder(itemView: View) : BindingViewHolder<ItemUserBinding>(itemView) {
        override fun onBind(position: Int) {
            val data = items.get(position)
            with (binding) {
                nickname.text = data.nickName
                introduction.text = data.introduction
            }
        }
    }

}