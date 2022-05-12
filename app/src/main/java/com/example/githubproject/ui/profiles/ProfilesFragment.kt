package com.example.githubproject.ui.profiles

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubproject.R
import com.example.githubproject.databinding.FragmentProfilesBinding
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.ui.BaseFragment
import com.example.githubproject.ui.utils.AppState
import com.example.githubproject.ui.utils.createErrSnackBar
import com.example.githubproject.ui.utils.createMsgSnackBar
import com.example.githubproject.ui.utils.hideSnackBar
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilesFragment :
    BaseFragment<FragmentProfilesBinding>(FragmentProfilesBinding::inflate) {

    private lateinit var adapter: ProfilesAdapter


    private val viewModel: ProfilesViewModelAbs by viewModel()

    private val controller by lazy { activity as Controller }
    private var retryIter: Int = 0
    private var snackBar: Snackbar? = null

    interface Controller {
        fun openProfileDetailsScreen(profile: UserProfile)
    }

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
        adapter = ProfilesAdapter {
            controller.openProfileDetailsScreen(it)
        }

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
            else -> {
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is Controller) {
            throw IllegalStateException(getString(R.string.activity_on_attach_err))
        }
    }

}