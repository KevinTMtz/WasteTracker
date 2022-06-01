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
import com.bruwus.wastetracker.ui.calculate.data.FragmentAndArgs
import com.bruwus.wastetracker.ui.calculate.data.QuestionArgs
import com.bruwus.wastetracker.ui.calculate.questions.NumberQuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.QuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.RadioQuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.SliderQuestionFragment
import com.bruwus.wastetracker.utils.general.makeToast

class CalculateFragment : Fragment() {
    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!

    private lateinit var fragmentsToInstantiate: List<FragmentAndArgs>
    private lateinit var questions: MutableList<QuestionFragment>

    private var currentQuestion = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)

        fragmentsToInstantiate = listOf(
            FragmentAndArgs(SliderQuestionFragment(), QuestionArgs(getString(R.string.calculate_question_1), -2.0)),
            FragmentAndArgs(RadioQuestionFragment(), QuestionArgs(getString(R.string.calculate_question_2), 5.0)),
            FragmentAndArgs(SliderQuestionFragment(), QuestionArgs(getString(R.string.calculate_question_3), -2.0)),
            FragmentAndArgs(RadioQuestionFragment(), QuestionArgs(getString(R.string.calculate_question_4), 15.0)),
            FragmentAndArgs(RadioQuestionFragment(), QuestionArgs(getString(R.string.calculate_question_5), 15.0)),
            FragmentAndArgs(NumberQuestionFragment(), QuestionArgs(getString(R.string.calculate_question_6), 0.5)),
            FragmentAndArgs(SliderQuestionFragment(), QuestionArgs(getString(R.string.calculate_question_7), 20.0)),
            FragmentAndArgs(NumberQuestionFragment(), QuestionArgs(getString(R.string.calculate_question_8), 10.0)),
        )

        questions = mutableListOf()

        fragmentsToInstantiate.forEachIndexed { index, fragmentAndArgs ->
            val args = Bundle()

            fragmentAndArgs.args.number = index + 1
            args.putSerializable("questionArgs", fragmentAndArgs.args)

            fragmentAndArgs.fragment.arguments = args

            questions.add(fragmentAndArgs.fragment)
        }

        activity?.supportFragmentManager?.commit {
            currentQuestion = 0
            replace(R.id.calculate_linear_layout, questions[currentQuestion], "fragment_question_${currentQuestion + 1}")
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
        if (currentQuestion > 0) {
            currentQuestion--

            checkIfShowBackButton(currentQuestion)

            activity?.supportFragmentManager?.commit {
                replace(R.id.calculate_linear_layout, questions[currentQuestion], "fragment_question_${currentQuestion + 1}")
                setReorderingAllowed(true)
            }
        }
    }

    private fun getNextQuestion() {

        if (!questions[currentQuestion].isAnswered()) {
            makeToast(
                requireActivity(),
                getString(R.string.calculate_validate_question),
                Toast.LENGTH_SHORT
            )
            return
        }

        currentQuestion++
        checkIfShowBackButton(currentQuestion)

        if (currentQuestion == questions.size) {
            getResults()
            return
        }

        activity?.supportFragmentManager?.commit {
            replace(
                R.id.calculate_linear_layout,
                questions[currentQuestion],
                "fragment_question_${currentQuestion + 1}"
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