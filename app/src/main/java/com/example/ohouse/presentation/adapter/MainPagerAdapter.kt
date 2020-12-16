package com.example.ohouse.presentation.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.ohouse.presentation.fragment.HomeFragment
import com.example.ohouse.presentation.fragment.PhotoFeedFragment


class MainPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TAB_TITLES = arrayOf(
        "홈",
        "사진피드"
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            SERVICE_TYPE.HOME.pagerIndex -> {
                HomeFragment()
            }
            else -> {
                PhotoFeedFragment()
            }
        }
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    override fun getPageTitle(position: Int): CharSequence? =
        TAB_TITLES.get(position)
}

enum class SERVICE_TYPE(val pagerIndex: Int) {
    HOME(0),
    FAVORITE(1)
}