package com.zachtib.locker.ui.screens.home

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : FragmentView(R.layout.fragment_home) {

    val viewModel by viewModel<HomeViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        notesLabel.onClick {
            findNavController().navigate(R.id.notesListFragment)
        }
        cameraTestButton.onClick {
            findNavController().navigate(R.id.visionTestFragment)
        }
    }
}