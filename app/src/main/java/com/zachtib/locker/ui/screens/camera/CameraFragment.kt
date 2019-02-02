package com.zachtib.locker.ui.screens.camera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.zachtib.locker.R
import com.zachtib.locker.framework.camerakit.captureImageAsync
import com.zachtib.locker.framework.ui.FragmentView
import kotlinx.android.synthetic.main.fragment_camera.*


class CameraFragment : FragmentView(R.layout.fragment_camera) {

    private lateinit var viewModel: CameraViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity()).get(CameraViewModel::class.java)

        takePictureButton.onClick {
            val image = camera.captureImageAsync()
            viewModel.setPhotoData(image)
            findNavController().navigateUp()
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
        camera.onStart()
    }

    override fun onResume() {
        super.onResume()
        camera.onResume()
    }

    override fun onPause() {
        camera.onPause()
        super.onPause()
    }

    override fun onStop() {
        camera.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onStop()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        camera.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}