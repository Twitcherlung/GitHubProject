package com.example.githubproject.ui.profiles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.domain.usecase.ProfilesUseCase
import com.example.githubproject.ui.utils.AppState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ProfilesViewModel(
    private val profilesUseCaseImpl: ProfilesUseCase
) : ViewModel(), ProfilesContract.ViewModel {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getLiveData(): MutableLiveData<AppState> = liveData

    override fun getUserAccounts() {
        liveData.postValue(AppState.Loading)
        compositeDisposable.add(
            profilesUseCaseImpl
                .getProfiles()
                .subscribeBy(
                    onSuccess = {
                        liveData.postValue(AppState.SuccessProfiles(it))
                    },
                    onError = {
                        liveData.postValue(AppState.Error(it))
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}