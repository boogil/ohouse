package com.example.ohouse.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.ohouse.AndroidApplication.Companion.LOGGED_IN
import com.example.ohouse.R
import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.data.Card
import com.example.ohouse.data.data.Home
import com.example.ohouse.data.data.PhotoDetail
import com.example.ohouse.data.data.User
import com.example.ohouse.databinding.ActivityMainBinding
import com.example.ohouse.presentation.adapter.MainPagerAdapter
import com.example.ohouse.presentation.viewmodel.OhouseViewModel
import com.example.ohouse.core.extension.failure
import com.example.ohouse.core.extension.observe
import com.example.ohouse.core.extension.onThrottleClick
import com.example.ohouse.core.functional.Utils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>() {
    private val ohouseViewModel: OhouseViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ohouseViewModel.apply {
            observe(home, this@MainActivity::handleSuccess)
            observe(photoDetail, this@MainActivity::handleSuccess)
            observe(photoFeeds, this@MainActivity::handleSuccess)
            observe(userDetail, this@MainActivity::handleSuccess)
            failure(failure, ::handleFailure)
        }.run {
            getHome()
            getPhotoFeed(1, 20)
        }

        with (binding) {
            viewpager.adapter = MainPagerAdapter(this@MainActivity, supportFragmentManager)
            tabLayout.setupWithViewPager(viewpager)

            loginBtn.onThrottleClick {
                startActivity(SigningActivity.getIntent(this@MainActivity, isSignUp = false))
            }
            signUpBtn.onThrottleClick {
                startActivity(SigningActivity.getIntent(this@MainActivity, isSignUp = true))
            }
            signOutBtn.onThrottleClick {
                LOGGED_IN = false
                afterLoginGroup.visibility = View.GONE
                beforeLoginGroup.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()

        checkLoggedIn()
    }

    /**
     * 로그인이 되었는지 체크하고 뷰를 변경한다.
     */
    private fun checkLoggedIn() {
        with (binding) {
            if (LOGGED_IN) {
                afterLoginGroup.visibility = View.VISIBLE
                beforeLoginGroup.visibility = View.GONE
            } else {
                afterLoginGroup.visibility = View.GONE
                beforeLoginGroup.visibility = View.VISIBLE
            }
        }
    }

    private fun handleSuccess(home: Home?) {}

    private fun handleSuccess(photoDetail: PhotoDetail?) {}

    private fun handleSuccess(photoFeeds: ArrayList<Card>?) {}

    private fun handleSuccess(userDetail: User?) {}

    private fun handleFailure(failure: Failure?) {
        Timber.v("handleFailure: %s", failure.toString())

        Utils.toast(this, "Network Connect Error")
    }
}