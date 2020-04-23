package com.firozanwar.mvvmbybilal.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.firozanwar.mvvmbybilal.data.repository.UserRepository
import com.firozanwar.mvvmbybilal.util.ApiExceptions
import com.firozanwar.mvvmbybilal.util.Coroutines
import com.firozanwar.mvvmbybilal.util.NoInternetExceptions

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email and password ")
            return
        }

        Coroutines.main {

            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(authResponse.user!!)
                    repository.saveUser(it)
                    return@let
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiExceptions) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetExceptions) {
                authListener?.onFailure(e.message!!)
            }


            /*val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful) {
                authListener?.onSuccess(response.body()?.user!!)
            } else {
                authListener?.onFailure("Error Code: ${response.code()}")
            }*/
        }
    }

    fun onSignupButtonClick(view: View) {
        authListener?.onStarted()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")
            return
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required")
            return
        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Password is required")
            return
        }

        if (password != passwordConfirm) {
            authListener?.onFailure("Password doesn't match")
            return
        }

        Coroutines.main {

            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(authResponse.user!!)
                    repository.saveUser(it)
                    return@let
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiExceptions) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetExceptions) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onSignup(view: View) {

        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}