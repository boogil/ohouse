package com.example.ohouse.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ohouse.AndroidApplication.Companion.LOGGED_IN
import com.example.ohouse.R
import com.example.ohouse.core.exception.Failure
import com.example.ohouse.databinding.ActivitySigningBinding
import com.example.ohouse.presentation.viewmodel.OhouseViewModel
import com.example.ohouse.core.extension.failure
import com.example.ohouse.core.extension.observe
import com.example.ohouse.core.extension.onThrottleClick
import com.example.ohouse.core.functional.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_user.*
import timber.log.Timber

@AndroidEntryPoint
class SigningActivity : BindingActivity<ActivitySigningBinding>() {
    private val ohouseViewModel: OhouseViewModel by viewModels()
    private var isSignUp = false

    override fun getLayoutResId(): Int = R.layout.activity_signing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isSignUp = intent.getBooleanExtra(PARAM_1, false)

        ohouseViewModel.apply {
            observe(signInResult, this@SigningActivity::handleSuccessSignIn)
            observe(signUpResult, this@SigningActivity::handleSuccessSignUp)
            failure(failure, ::handleFailure)
        }

        if (!isSignUp) {
            introduction.visibility = View.GONE
            binding.signBtn.text = "로그인"
        }

        with(binding) {
            signBtn.onThrottleClick {
                if (isSignUp) {
                    ohouseViewModel.signUp(
                        nickname.text.toString(),
                        introduction.text.toString(),
                        pwd.text.toString()
                    )
                } else {
                    ohouseViewModel.signIn(
                        nickname.text.toString(),
                        pwd.text.toString()
                    )
                }
            }
        }
    }

    private fun handleSuccessSignIn(signInId: String?) {
        if (signInId.isNullOrEmpty()) return
        onBackPressed()
        LOGGED_IN = true
    }

    private fun handleSuccessSignUp(signUpId: String?) {
        if (signUpId.isNullOrEmpty()) return
        onBackPressed()
        LOGGED_IN = true
    }

    private fun handleFailure(failure: Failure?) {
        Timber.v("handleFailure: %s", failure.toString())

        if (failure is Failure.ApiError) {
            Utils.toast(this, failure.msg)
        } else {
            Utils.toast(this, "Network Connect Error")
        }

    }

    companion object {
        const val PARAM_1 = "PARAM_1"
        fun getIntent(context: Context, isSignUp: Boolean = true): Intent {
            return Intent(context, SigningActivity::class.java).apply {
                putExtra(PARAM_1, isSignUp)
            }
        }
    }
}