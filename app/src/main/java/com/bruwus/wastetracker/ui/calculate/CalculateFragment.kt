package com.bruwus.wastetracker.ui.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentCalculateBinding
import com.bruwus.wastetracker.ui.calculate.questions.QuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.RadioQuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.SliderQuestionFragment

class CalculateFragment : Fragment() {
    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!

    private var questions = listOf<QuestionFragment>(
        SliderQuestionFragment(1, "How often do you recycle?"),
        RadioQuestionFragment(2, "Do you own an electric car?"),
        SliderQuestionFragment(3, "How often do you reuse?", 5.0)
    )
    private var i = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        val score = questions.sumOf { it.getScore() }
        // TODO: send score to next fragment
        findNavController().navigate(R.id.action_navigation_calculate_to_resultsFragment)
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

        checkIfShowBackButton(i + 1)

        if (i == questions.size - 1) {
            getResults()
            return
        }

        if (questions[i].isAnswered()) {
            i++
            activity?.supportFragmentManager?.commit {
                replace(
                    R.id.calculate_linear_layout,
                    questions[i],
                    "fragment_question_${i + 1}"
                )
                setReorderingAllowed(true)
            }
        } else {
            Toast.makeText(
                context,
                "Please, answer the question before continuing",
                Toast.LENGTH_SHORT
            ).show()
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