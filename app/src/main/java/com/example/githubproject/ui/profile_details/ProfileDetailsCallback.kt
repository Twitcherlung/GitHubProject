package com.example.githubproject.ui.profile_details

import androidx.recyclerview.widget.DiffUtil
import com.example.githubproject.domain.entities.UserRepository
import com.example.githubproject.ui.utils.Change

class ProfileDetailsCallback(
    private val newItems: List<UserRepository>,
    private val oldItems: List<UserRepository>
) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newItems[oldItemPosition].id == oldItems[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[newItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.name == newItem.name
                && oldItem.fullName == newItem.fullName
                && oldItem.htmlUrl == newItem.htmlUrl
                && oldItem.description == newItem.description
                && oldItem.language == newItem.language
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return Change(oldItem, newItem)
    }

}