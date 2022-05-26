package com.bruwus.wastetracker.ui.learn.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bruwus.wastetracker.databinding.FragmentInformationBinding
import com.bruwus.wastetracker.ui.home.browser.InAppBrowser
import com.bruwus.wastetracker.ui.learn.data.RecycleTip
import com.bruwus.wastetracker.ui.learn.data.RecyclerViewData
import com.bruwus.wastetracker.ui.learn.data.Tool3D
import com.bruwus.wastetracker.ui.learn.data.WasteType
import com.bumptech.glide.Glide

class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInformationBinding.inflate(inflater, container, false)

        val data = arguments?.getSerializable("data") as RecyclerViewData

        binding.infoTitleTextView.text = data.title
        binding.infoDescriptionTextView.text = data.description
        Glide.with(binding.root)
            .load(data.imageUrl).centerCrop()
            .into(binding.infoImage)

        when (data) {
            is WasteType -> {
                binding.infoCategoryTextView.text = "Waste type"
            }
            is RecycleTip -> {
                binding.infoCategoryTextView.text = "Recycle tip"
            }
            is Tool3D -> {
                binding.infoCategoryTextView.text = "3D tool"

                binding.infoOpenBrowserButton.setOnClickListener {
                    data.webpageUrl?.let { it ->
                        InAppBrowser.open(binding.root.context, it)
                    }
                }

                binding.infoOpenBrowserButton.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().onBackPressed()

        return super.onOptionsItemSelected(item)
    }
}