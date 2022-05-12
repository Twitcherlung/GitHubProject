package com.example.githubproject.ui.profiles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.ui.utils.AppState

abstract class ProfilesViewModelAbs: ViewModel() {
    abstract fun getLiveData() : MutableLiveData<AppState>
    abstract fun getUserAccounts()

}