package com.zachtib.locker.ui.screens.home

import android.os.Bundle
import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : FragmentView(R.layout.fragment_home) {

    val viewModel by viewModel<HomeViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}