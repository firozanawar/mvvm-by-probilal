package com.firozanwar.mvvmbybilal.ui.auth

import androidx.lifecycle.LiveData
import com.firozanwar.mvvmbybilal.data.db.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user: User)

    fun onFailure(message: String)
}