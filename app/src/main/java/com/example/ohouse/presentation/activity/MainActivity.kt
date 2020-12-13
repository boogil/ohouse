package com.example.ohouse.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ohouse.R
import com.example.ohouse.core.di.RetrofitModule
import com.example.ohouse.domain.OhouseRepository
import com.example.ohouse.domain.service.OhouseService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var ohouseRepository: OhouseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b = 10
        GlobalScope.launch() {
            val retVal1 = ohouseRepository.network.signUp("a", "b", "c")
            val retVal2 = ohouseRepository.network.getHome()
            val a = 3
        }

        val a = 3
    }
}