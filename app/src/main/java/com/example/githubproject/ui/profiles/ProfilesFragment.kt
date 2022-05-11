package com.example.githubproject.ui.profiles

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubproject.R
import com.example.githubproject.app
import com.example.githubproject.databinding.FragmentProfilesBinding
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.ui.BaseFragment
import com.example.githubproject.ui.NavigationActivity
import com.example.githubproject.ui.profile_details.ProfileDetailsFragment
import com.example.githubproject.ui.utils.AppState
import com.example.githubproject.ui.utils.createErrSnackBar
import com.example.githubproject.ui.utils.createMsgSnackBar
import com.example.githubproject.ui.utils.hideSnackBar
import com.google.android.material.snackbar.Snackbar

class ProfilesFragment:
    BaseFragment<FragmentProfilesBinding>(FragmentProfilesBinding::inflate) {

    private lateinit var adapter: ProfilesAdapter

    private val viewModel: ProfilesContract.ViewModel by lazy {
        ProfilesViewModel(requireActivity().app.profilesUseCaseImpl)
    }

    private var retryIter: Int = 0
    private var snackBar: Snackbar? = null

    companion object {
        fun newInstance() = ProfilesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        connectSignals()
        viewModel.getUserAccounts()

    }

    private fun setupUi() {
        adapter = ProfilesAdapter()
        adapter.setOnClick(object : ProfilesAdapter.OnClick {
            override fun onClick(profile: UserProfile) {
                activity?.let {
                    if (it is NavigationActivity) {
                        it.navigationTo(ProfileDetailsFragment.newInstance(profile), true)
                    }
                }
            }
        })

        binding.profilesRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.profilesRecycler.adapter = adapter

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getUserAccounts()
        }
    }


    private fun connectSignals() {
        viewModel.getLiveData().observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun renderData(result: AppState) {
        binding.swipeToRefresh.isRefreshing = false
        when (result) {
            is AppState.Loading -> {
                hideSnackBar(snackBar)
                with(binding) {
                    emptyTextView.isVisible = false
                    swipeToRefresh.isRefreshing = true
                }
            }
            is AppState.SuccessProfiles -> {
                retryIter = 0
                binding.emptyTextView.isVisible = result.profiles.isEmpty()
                adapter.setData(result.profiles)
            }
            is AppState.Error -> {
                binding.emptyTextView.isVisible = true
                if (retryIter < 3) {
                    snackBar = binding.root.createErrSnackBar(
                        text = result.error.toString(),
                        actionText = R.string.retry,
                        { viewModel.getUserAccounts() }
                    )
                    snackBar?.show()
                } else {
                    binding.root.createMsgSnackBar(
                        text = this.resources.getString(R.string.fall_load_data)
                    ).show()
                }
                retryIter++
            }
            else -> {}
        }
    }

}