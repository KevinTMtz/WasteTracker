package com.bruwus.wastetracker.ui.identify

import android.app.Activity.RESULT_OK
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
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentIdentifyBinding
import com.bruwus.wastetracker.ui.utils.browser.InAppBrowser
import com.bruwus.wastetracker.ui.utils.feedback.LoadingIndicator
import com.bruwus.wastetracker.utils.general.makeToast

class IdentifyFragment : Fragment() {
    private var _binding: FragmentIdentifyBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: IdentifyViewModel

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private lateinit var loadingDialog: LoadingIndicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdentifyBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[IdentifyViewModel::class.java]

        viewModel.wasteIdentification.observe(viewLifecycleOwner) { identifyWasteResult ->
            identifyWasteResult?.let {
                loadingDialog.show(false)

                binding.resultCard.visibility = View.VISIBLE

                binding.titleTextView.text = identifyWasteResult.name
                binding.certaintyTextView.text = getString(R.string.identify_certainty, identifyWasteResult.certainty)
                binding.descriptionTextView.text = identifyWasteResult.description

                binding.identifyLearnMoreButton.setOnClickListener {
                    InAppBrowser.open(binding.root.context, identifyWasteResult.howTo)
                }
            } ?: run {
                loadingDialog.show(false)

                makeToast(requireActivity(), getString(R.string.identify_error_identifying), Toast.LENGTH_SHORT)
                cleanUI()
            }
        }

        binding.takePictureButton.setOnClickListener {
            cameraIntent()
        }

        binding.takeAnotherButton.setOnClickListener {
            cleanUI()
        }

        loadingDialog = LoadingIndicator(requireActivity())

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
        loadingDialog.show(true)

        viewModel.imageBitmap?.let {
            viewModel.uploadPhotoToFirebase (
                it,
                { storageReference, imageUrl ->
                    viewModel.identifyWaste(imageUrl, storageReference)
                }, {
                    loadingDialog.show(false)
                    
                    cleanUI()
                    makeToast(requireActivity(), getString(R.string.identify_error_upload_photo), Toast.LENGTH_LONG)
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

    private fun cleanUI() {
        binding.takePictureButton.visibility = View.VISIBLE
        binding.identifyTakePictureTextView.visibility = View.VISIBLE

        binding.capturedImageCard.visibility = View.GONE
        binding.resultCard.visibility = View.GONE
    }
}