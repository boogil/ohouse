package com.example.ohouse.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.ohouse.AndroidApplication.Companion.LOGGED_IN
import com.example.ohouse.R
import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.data.PhotoDetail
import com.example.ohouse.databinding.ActivityCardDetailBinding
import com.example.ohouse.databinding.ActivitySigningBinding
import com.example.ohouse.presentation.viewmodel.OhouseViewModel
import com.example.ohouse.core.extension.failure
import com.example.ohouse.core.extension.observe
import com.example.ohouse.core.extension.onThrottleClick
import com.example.ohouse.core.functional.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_photo_feed.*
import timber.log.Timber

@AndroidEntryPoint
class CardDetailActivity : BindingActivity<ActivityCardDetailBinding>() {
    private val ohouseViewModel: OhouseViewModel by viewModels()
    private var id: Int = -1

    override fun getLayoutResId(): Int = R.layout.activity_card_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getIntExtra(PARAM_1, -1)

        ohouseViewModel.apply {
            observe(photoDetail, this@CardDetailActivity::handleSuccess)
            failure(failure, ::handleFailure)

            getPhotoDetail(id)
        }
    }

    private fun handleSuccess(photoDetail: PhotoDetail?) {
        with(binding) {
            photoDetail?.let {
                Glide.with(this@CardDetailActivity).load(it.card.imgUrl).into(img)
                title.text = it.card.desc
            }
        }
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
        fun getIntent(context: Context, id: Int): Intent {
            return Intent(context, CardDetailActivity::class.java).apply {
                putExtra(PARAM_1, id)
            }
        }
    }
}