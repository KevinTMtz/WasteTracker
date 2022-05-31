package com.bruwus.wastetracker.ui.calculate.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentResultsBinding
import com.bruwus.wastetracker.ui.calculate.data.CalculatorData
import com.bruwus.wastetracker.ui.calculate.data.ResultLevel
import com.bumptech.glide.Glide

class ResultsFragment : Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val resultType = mapOf(
            0 to ResultLevel(
                getString(R.string.results_less_than),
                getString(R.string.results_advice_less_than),
                "https://firebasestorage.googleapis.com/v0/b/waste-tracker-8df4b.appspot.com/o/resultType%2Fless_than.jpg?alt=media&token=e85a63ea-afdf-4905-ba26-10199b5bf292"
            ),
            1 to ResultLevel(
                getString(R.string.results_average),
                getString(R.string.results_advice_average),
                "https://firebasestorage.googleapis.com/v0/b/waste-tracker-8df4b.appspot.com/o/resultType%2Faverage.jpg?alt=media&token=f40e90b0-af63-457e-a5f2-9162f793ff66"
            ),
            2 to ResultLevel(
                getString(R.string.results_more_than_average),
                getString(R.string.results_advice_more_than_average),
                "https://firebasestorage.googleapis.com/v0/b/waste-tracker-8df4b.appspot.com/o/resultType%2Fmore_than.jpg?alt=media&token=52db360b-ee4d-4806-a898-61fdeb0fce72"
            )
        )

        _binding = FragmentResultsBinding.inflate(inflater, container, false)

        val data = arguments?.getSerializable("data") as CalculatorData

        binding.resultText.text = getString(R.string.results_kilograms, data.result)
        binding.imageDescription.text = resultType[data.consumerType]?.title

        var textColor = 0
        when (data.consumerType) {
            0 -> textColor = ContextCompat.getColor(requireActivity(), R.color.good)
            1 -> textColor = ContextCompat.getColor(requireActivity(), R.color.bad)
            2 -> textColor = ContextCompat.getColor(requireActivity(), R.color.bad)
        }
        binding.imageDescription.setTextColor(textColor)

        binding.resultAdvice.text = resultType[data.consumerType]?.advice
        Glide.with(binding.root)
            .load(resultType[data.consumerType]?.imageUrl).centerCrop()
            .into(binding.resultImage)

        binding.learnButton.setOnClickListener {
            findNavController().navigate(R.id.action_results_to_navigation_learn)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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