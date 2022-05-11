package com.example.githubproject.ui.profiles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubproject.databinding.FragmentProfileItemBinding
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.ui.utils.Change
import com.example.githubproject.ui.utils.createCombinePayloads

class ProfilesAdapter : RecyclerView.Adapter<ProfilesAdapter.ProfileViewHolder>() {

    private var listData: MutableList<UserProfile> = arrayListOf()

    fun setData(data: List<UserProfile>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilsCallback(listData, data))
        diffResult.dispatchUpdatesTo(this)
        listData.clear()
        listData.addAll(data)
    }

    private var onClick: OnClick? = null

    fun getOnClick() = onClick

    fun setOnClick(onClick: OnClick) {
        this.onClick = onClick
    }

    interface OnClick {
        fun onClick(profile: UserProfile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = FragmentProfileItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfileViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val change = createCombinePayloads(payloads as List<Change<UserProfile>>)
            val oldData = change.oldData
            val newData = change.newData

            if (oldData.name != newData.name){
                FragmentProfileItemBinding.bind(holder.itemView).nameTextView.text =
                    newData.name
            }

            if (oldData.login != newData.login){
                FragmentProfileItemBinding.bind(holder.itemView).loginTextView.text =
                    newData.login
            }

        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    inner class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: UserProfile) {
            FragmentProfileItemBinding.bind(itemView).apply {
                avatarImageView.load(data.avatarUrl)
                loginTextView.text = data.login
                nameTextView.text = data.name
                itemView.setOnClickListener {
                    getOnClick()?.onClick(data)
                }
            }
        }
    }

}