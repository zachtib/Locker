package com.zachtib.locker.ui.screens.camera

import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import kotlinx.android.synthetic.main.fragment_camera.*

class CameraFragment : FragmentView(R.layout.fragment_camera) {
    override fun onStart() {
        super.onStart()
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
        super.onStop()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        camera.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}