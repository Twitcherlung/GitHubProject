package com.example.githubproject.ui.profile_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.databinding.FragmentRepoItemBinding
import com.example.githubproject.domain.entities.UserRepository
import com.example.githubproject.ui.utils.Change
import com.example.githubproject.ui.utils.createCombinePayloads

class ProfileDetailsAdapter : RecyclerView.Adapter<ProfileDetailsAdapter.RepoViewHolder>() {

    private var listData: MutableList<UserRepository> = arrayListOf()

    fun setData(data: List<UserRepository>) {
        val diffResult = DiffUtil.calculateDiff(ProfileDetailsCallback(listData, data))
        diffResult.dispatchUpdatesTo(this)
        listData.clear()
        listData.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = FragmentRepoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(
        holder: RepoViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val change = createCombinePayloads(payloads as List<Change<UserRepository>>)
            val oldData = change.oldData
            val newData = change.newData

            if (oldData.name != newData.name) {
                FragmentRepoItemBinding.bind(holder.itemView).titleRepoTextView.text =
                    newData.name
            }

            if (oldData.description != newData.description) {
                newData.description?.let {
                    FragmentRepoItemBinding.bind(holder.itemView).descriptionTextView.text = it
                }
            }

        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    inner class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: UserRepository) {
            FragmentRepoItemBinding.bind(itemView).apply {
                titleRepoTextView.text = data.name
                data.description?.let { descriptionTextView.text = it }
            }
        }
    }

}