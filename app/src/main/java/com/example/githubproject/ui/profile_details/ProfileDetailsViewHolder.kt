package com.example.githubproject.ui.profile_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.databinding.FragmentRepoItemBinding
import com.example.githubproject.domain.entities.UserRepository

class ProfileDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun createView(parent: ViewGroup): ProfileDetailsViewHolder {
            val binding = FragmentRepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ProfileDetailsViewHolder(binding.root)
        }
    }

    fun bind(data: UserRepository) {
        FragmentRepoItemBinding.bind(itemView).apply {
            titleRepoTextView.text = data.name
            data.description?.let { descriptionTextView.text = it }
        }
    }
}

