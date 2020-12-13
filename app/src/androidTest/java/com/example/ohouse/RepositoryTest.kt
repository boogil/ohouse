package com.example.ohouse

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.example.ohouse.domain.OhouseRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
class RepositoryTest  {

    @get: Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var ohouseRepository: OhouseRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun after() {

    }

    @Test
    fun repositoryTest() {

        val b = 10
        val retVal = ohouseRepository.network.signUp("a","b","c")


        val a = 3
    }

}