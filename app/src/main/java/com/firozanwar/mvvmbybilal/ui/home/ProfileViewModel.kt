package com.firozanwar.mvvmbybilal.ui.home

import androidx.lifecycle.ViewModel
import com.firozanwar.mvvmbybilal.data.repository.UserRepository

class ProfileViewModel(userRepository: UserRepository) : ViewModel() {

    val user = userRepository.getUser()

}
