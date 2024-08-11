package com.klvnd.mdintern.ui.thirdscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.klvnd.mdintern.data.response.DataItem
import com.klvnd.mdintern.databinding.ItemUserBinding

class UserAdapter(
    private var users: List<DataItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newUsers: List<DataItem>) {
        this.users = newUsers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

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
}
