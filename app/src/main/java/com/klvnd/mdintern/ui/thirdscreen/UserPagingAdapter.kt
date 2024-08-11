package com.klvnd.mdintern.ui.thirdscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.klvnd.mdintern.data.response.DataItem
import com.klvnd.mdintern.databinding.ItemUserBinding

class UserPagingAdapter(
    private val listener: OnItemClickListener
) : PagingDataAdapter<DataItem, UserPagingAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: DataItem) {
            Glide.with(binding.root.context)
                .load(user.avatar)
                .into(binding.userImage)

            binding.tvUsername.text = "${user.firstName} ${user.lastName}"
            binding.tvEmail.text = user.email

            binding.root.setOnClickListener {
                listener.onItemClick(user)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(user: DataItem)
    }

    private class UserDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }
}
