package com.bruwus.wastetracker.ui.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentCalculateBinding
import com.bruwus.wastetracker.ui.calculate.data.CalculatorData
import com.bruwus.wastetracker.ui.calculate.questions.NumberQuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.QuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.RadioQuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.SliderQuestionFragment
import com.bruwus.wastetracker.utils.general.makeToast

class CalculateFragment : Fragment() {
    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!

    private lateinit var questions: List<QuestionFragment>
    private var i = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        questions = listOf(
            SliderQuestionFragment(1, getString(R.string.calculate_question_1)),
            RadioQuestionFragment(2, getString(R.string.calculate_question_2)),
            SliderQuestionFragment(3, getString(R.string.calculate_question_3), 5.0),
            NumberQuestionFragment(4, getString(R.string.calculate_question_4), 15.0)
        )

        _binding = FragmentCalculateBinding.inflate(inflater, container, false)

        activity?.supportFragmentManager?.commit {
            i = 0
            replace(R.id.calculate_linear_layout, questions[i], "fragment_question_${i + 1}")
            setReorderingAllowed(true)
        }

        binding.backButton.setOnClickListener {
            getPrevQuestion()
        }

        binding.nextButton.setOnClickListener {
            getNextQuestion()
        }

        return binding.root
    }

    private fun getResults() {
        val result = questions.sumOf { it.getScore() }
        val data = CalculatorData(result)
        val bundle = bundleOf("data" to data)
        findNavController().navigate(R.id.action_navigation_calculate_to_resultsFragment, bundle)
    }

    private fun getPrevQuestion() {
        if (i > 0) {
            i--

            checkIfShowBackButton(i)

            activity?.supportFragmentManager?.commit {
                replace(R.id.calculate_linear_layout, questions[i], "fragment_question_${i + 1}")
                setReorderingAllowed(true)
            }
        }
    }

    private fun getNextQuestion() {

        if (!questions[i].isAnswered()) {
            makeToast(
                requireActivity(),
                getString(R.string.calculate_validate_question),
                Toast.LENGTH_SHORT
            )
            return
        }

        i++
        checkIfShowBackButton(i)

        if (i == questions.size) {
            getResults()
            return
        }

        activity?.supportFragmentManager?.commit {
            replace(
                R.id.calculate_linear_layout,
                questions[i],
                "fragment_question_${i + 1}"
            )
            setReorderingAllowed(true)

        }

    }

    private fun checkIfShowBackButton(pos: Int) {
        if (pos < 1) {
            binding.backButton.visibility = View.GONE
        } else {
            binding.backButton.visibility = View.VISIBLE
        }
    }
}