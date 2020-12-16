package com.example.ohouse.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.ohouse.R
import com.example.ohouse.databinding.FragmentHomeBinding
import com.example.ohouse.presentation.viewmodel.OhouseViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    private val ohouseViewModel: OhouseViewModel by activityViewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ohouseVm = ohouseViewModel
    }
}