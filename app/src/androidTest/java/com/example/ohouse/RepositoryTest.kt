package com.example.ohouse

import com.example.ohouse.domain.repository.OhouseRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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