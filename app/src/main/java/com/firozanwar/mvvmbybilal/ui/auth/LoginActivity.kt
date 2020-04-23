package com.firozanwar.mvvmbybilal.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.firozanwar.mvvmbybilal.R
import com.firozanwar.mvvmbybilal.data.db.entities.User
import com.firozanwar.mvvmbybilal.databinding.ActivityLoginBinding
import com.firozanwar.mvvmbybilal.ui.home.HomeActivity
import com.firozanwar.mvvmbybilal.util.hide
import com.firozanwar.mvvmbybilal.util.show
import com.firozanwar.mvvmbybilal.util.snackbar
import com.firozanwar.mvvmbybilal.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by this.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel;
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null)
                toast("Logged In")
        })
    }

    override fun onStarted() {
        progress_bar.show()
        root_layout.snackbar("Login Started")
    }

    override fun onSuccess(user: User) {
        root_layout.snackbar("${user.name} is logged in")
        progress_bar.hide()

        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)

        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
}
