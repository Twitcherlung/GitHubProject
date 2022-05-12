package com.example.githubproject.ui.profiles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubproject.databinding.FragmentProfileItemBinding
import com.example.githubproject.domain.entities.UserProfile

class ProfilesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun createView(parent: ViewGroup): ProfilesViewHolder {
            val binding = FragmentProfileItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ProfilesViewHolder(binding.root)
        }
    }

    fun bind(data: UserProfile, listener: (UserProfile) -> Unit) {
        FragmentProfileItemBinding.bind(itemView).apply {
            avatarImageView.load(data.avatarUrl)
            loginTextView.text = data.login
            nameTextView.text = data.name
            itemView.setOnClickListener {
                listener.invoke(data)
            }
        }
    }
}