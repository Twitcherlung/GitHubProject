package com.example.githubproject.ui.profile_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.ui.utils.AppState

abstract class ProfileDetailsViewModelAbs : ViewModel() {
    abstract fun getLiveData(): MutableLiveData<AppState>
    abstract fun getRepos(loginProfile: String)
}