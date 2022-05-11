package com.example.githubproject.ui.profile_details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.githubproject.R
import com.example.githubproject.app
import com.example.githubproject.databinding.FragmentProfileDetailsBinding
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.ui.BaseFragment
import com.example.githubproject.ui.utils.AppState
import com.example.githubproject.ui.utils.createErrSnackBar
import com.example.githubproject.ui.utils.createMsgSnackBar
import com.example.githubproject.ui.utils.hideSnackBar
import com.google.android.material.snackbar.Snackbar

class ProfileDetailsFragment : BaseFragment<FragmentProfileDetailsBinding>(FragmentProfileDetailsBinding::inflate) {

    private lateinit var adapter: ProfileDetailsAdapter

    var profile: UserProfile? = null

    private val viewModel: ProfileDetailsContract.ViewModel by lazy {
        ProfileDetailsViewModel(
            requireActivity().app.repositoryUseCaseImpl
        )
    }

    private var retryIter: Int = 0
    private var snackBar: Snackbar? = null

    companion object {
        const val ARG_PARAM = "login profile"

        fun newInstance(profile: UserProfile): ProfileDetailsFragment {
            return ProfileDetailsFragment().also { fragment ->
                fragment.arguments = Bundle().also { bundle ->
                    bundle.putParcelable(ARG_PARAM, profile)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile = arguments?.getParcelable(ARG_PARAM)

        setupUi()
        connectSignals()
        setProfileData()
        getData()
    }

    private fun setupUi() {
        adapter = ProfileDetailsAdapter()

        binding.repoRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.repoRecyclerView.adapter = adapter

    }

    private fun setProfileData() {
        profile?.let {
            binding.avatarImageView.load(it.avatarUrl)
            binding.loginTextView.text = it.login
        }
    }


    private fun connectSignals() {
        viewModel.getLiveData().observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun getData() {
        profile?.let {
            viewModel.getRepos(it.login)
        }
    }

    private fun renderData(result: AppState) {
        binding.progressBar.isVisible = false
        when (result) {
            is AppState.Loading -> {
                binding.progressBar.isVisible = true
                hideSnackBar(snackBar)
            }
            is AppState.SuccessRepos -> {
                adapter.setData(result.repos)
            }
            is AppState.Error -> {
                if (retryIter < 3) {
                    snackBar = binding.root.createErrSnackBar(
                        text = result.error.toString(),
                        actionText = R.string.retry,
                        { getData() }
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