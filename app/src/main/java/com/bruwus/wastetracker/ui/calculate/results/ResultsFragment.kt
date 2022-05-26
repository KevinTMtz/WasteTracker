package com.bruwus.wastetracker.ui.calculate.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentResultsBinding
import com.bruwus.wastetracker.ui.calculate.data.CalculatorData

class ResultsFragment : Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val consumerType = mapOf(
            0 to getString(R.string.results_less_than),
            1 to getString(R.string.results_average),
            2 to getString(R.string.results_more_than_average)
        )

        val advices = mapOf(
            0 to getString(R.string.results_advice_less_than),
            1 to getString(R.string.results_advice_average),
            2 to getString(R.string.results_advice_more_than_average)
        )

        _binding = FragmentResultsBinding.inflate(inflater, container, false)

        val data = arguments?.getSerializable("data") as CalculatorData

        binding.resultText.text = getString(R.string.results_kilograms, data.result)
        binding.imageDescription.text = consumerType[data.consumerType]
        binding.resultAdvice.text = advices[data.consumerType]

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