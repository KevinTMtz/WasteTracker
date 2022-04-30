package com.example.wastetracker.ui.identify

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wastetracker.databinding.FragmentIdentifyBinding

class IdentifyFragment : Fragment() {

    private var _binding: FragmentIdentifyBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdentifyBinding.inflate(inflater, container, false)

        binding.takePictureButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.takeAnotherButton.setOnClickListener {
            binding.takePictureButton.visibility = View.VISIBLE
            binding.capturedImageView.setImageBitmap(null)
            binding.resultCard.visibility = View.GONE

            dispatchTakePictureIntent()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // TODO: display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.capturedImageView.setImageBitmap(imageBitmap)

            binding.takePictureButton.visibility = View.GONE
            binding.resultCard.visibility = View.VISIBLE
        }
    }
}