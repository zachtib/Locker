package com.zachtib.locker.ui.screens.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.google.firebase.ml.vision.label.FirebaseVisionLabel
import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import com.zachtib.locker.framework.ui.SimpleListAdapter
import com.zachtib.locker.ui.screens.camera.CameraViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.tasks.asDeferred
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class HomeFragment : FragmentView(R.layout.fragment_home) {

    val viewModel by viewModel<HomeViewModel>()

    private val labeler = FirebaseVision.getInstance().visionLabelDetector

    private lateinit var labelAdapter: SimpleListAdapter<FirebaseVisionLabel>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        labelAdapter = SimpleListAdapter(object : DiffUtil.ItemCallback<FirebaseVisionLabel>() {
            override fun areItemsTheSame(oldItem: FirebaseVisionLabel, newItem: FirebaseVisionLabel): Boolean {
                return oldItem.label == newItem.label
            }

            override fun areContentsTheSame(oldItem: FirebaseVisionLabel, newItem: FirebaseVisionLabel): Boolean {
                return (oldItem.label == newItem.label) && (oldItem.confidence == newItem.confidence)
            }
        }) { label ->
            val percentage = DecimalFormat.getPercentInstance().format(label.confidence)
            "${label.label}: $percentage"
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = labelAdapter
        }

        addButton.onClick {
            val cameraViewModel = ViewModelProviders.of(requireActivity()).get(CameraViewModel::class.java)
            cameraViewModel.setMessage("Take a Photo")
            findNavController().navigate(R.id.action_homeScreen_to_cameraFragment)

            val bytes = cameraViewModel.waitForPhoto()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            val visionImage = FirebaseVisionImage.fromBitmap(bitmap)

            val labels: List<FirebaseVisionLabel> = labeler
                .detectInImage(visionImage)
                .asDeferred()
                .await()

            labelAdapter.submitList(labels)
        }
    }
}