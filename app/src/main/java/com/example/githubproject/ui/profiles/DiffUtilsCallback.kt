package com.example.githubproject.ui.profiles

import androidx.recyclerview.widget.DiffUtil
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.ui.utils.Change

class DiffUtilsCallback(
    private val newItems: List<UserProfile>,
    private val oldItems: List<UserProfile>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size
    override fun getNewListSize() = newItems.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newItems[newItemPosition].id == oldItems[oldItemPosition].id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[newItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.name == newItem.name
                && oldItem.login == newItem.login
                && oldItem.email == newItem.email
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        val newItem = newItems[newItemPosition]
        val oldItem = oldItems[oldItemPosition]
        return Change(oldItemPosition, newItemPosition)
    }
}