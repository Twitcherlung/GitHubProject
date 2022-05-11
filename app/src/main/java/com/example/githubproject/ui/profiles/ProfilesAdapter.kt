package com.example.githubproject.ui.profiles


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.databinding.FragmentProfileItemBinding
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.ui.utils.Change
import com.example.githubproject.ui.utils.createCombinePayloads

class ProfilesAdapter(
    private val itemClickCallback: (UserProfile) -> Unit
) : RecyclerView.Adapter<ProfilesViewHolder>() {

    private var listData: MutableList<UserProfile> = arrayListOf()

    fun setData(data: List<UserProfile>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilsCallback(listData, data))
        diffResult.dispatchUpdatesTo(this)
        listData.clear()
        listData.addAll(data)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {

        return ProfilesViewHolder.createView(parent)
    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        holder.bind(listData[position], itemClickCallback)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(
        holder: ProfilesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val change = createCombinePayloads(payloads as List<Change<UserProfile>>)
            val oldData = change.oldData
            val newData = change.newData

            if (oldData.name != newData.name) {
                FragmentProfileItemBinding.bind(holder.itemView).nameTextView.text =
                    newData.name
            }

            if (oldData.login != newData.login) {
                FragmentProfileItemBinding.bind(holder.itemView).loginTextView.text =
                    newData.login
            }

        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }


}
