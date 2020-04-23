package com.firozanwar.mvvmbybilal.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firozanwar.mvvmbybilal.data.repository.UserRepository
import com.firozanwar.mvvmbybilal.ui.home.ProfileViewModel

class ProfileViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }


}