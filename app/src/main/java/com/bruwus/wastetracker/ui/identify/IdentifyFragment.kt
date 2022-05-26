package com.bruwus.wastetracker.ui.identify

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bruwus.wastetracker.databinding.FragmentIdentifyBinding
import com.bruwus.wastetracker.ui.home.browser.InAppBrowser
import com.bruwus.wastetracker.utils.general.makeToast

class IdentifyFragment : Fragment() {
    private var _binding: FragmentIdentifyBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: IdentifyViewModel

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdentifyBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[IdentifyViewModel::class.java]

        viewModel.wasteIdentification.observe(viewLifecycleOwner) { identifyWasteResult ->
            identifyWasteResult?.let {
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }

                binding.resultCard.visibility = View.VISIBLE

                binding.titleTextView.text = identifyWasteResult.name
                binding.certaintyTextView.text = "Certainty: ${identifyWasteResult.certainty}"
                binding.descriptionTextView.text = identifyWasteResult.description

                binding.identifyLearnMoreButton.setOnClickListener {
                    InAppBrowser.open(binding.root.context, identifyWasteResult.howTo)
                }
            }
        }

        binding.takePictureButton.setOnClickListener {
            cameraIntent()
        }

        binding.takeAnotherButton.setOnClickListener {
            cleanUIAndData()
            cameraIntent()
        }

        progressDialog = ProgressDialog(this.context)

        initCameraLauncher()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCameraLauncher() {
        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                prepareUIToShowResult()

                viewModel.imageBitmap = result.data?.extras?.get("data") as Bitmap
                this.binding.capturedImageView.setImageBitmap(viewModel.imageBitmap)

                identifyWaste()
            }
        }
    }

    private fun identifyWaste() {
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        viewModel.imageBitmap?.let {
            viewModel.uploadPhotoToFirebase (
                it,
                { storageReference, imageUrl ->
                    viewModel.identifyWaste(imageUrl, storageReference)
                }, {
                    makeToast(requireActivity(), "Failed to upload photo", Toast.LENGTH_LONG)
                }
            )
        }
    }

    private fun cameraIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

    private fun prepareUIToShowResult() {
        binding.capturedImageCard.visibility = View.VISIBLE
        binding.takePictureButton.visibility = View.GONE
        binding.identifyTakePictureTextView.visibility = View.GONE
    }

    private fun cleanUIAndData() {
        binding.takePictureButton.visibility = View.VISIBLE
        binding.identifyTakePictureTextView.visibility = View.VISIBLE

        binding.capturedImageCard.visibility = View.GONE
        binding.resultCard.visibility = View.GONE

        viewModel.cleanData()
        binding.capturedImageView.setImageBitmap(null)
    }
}