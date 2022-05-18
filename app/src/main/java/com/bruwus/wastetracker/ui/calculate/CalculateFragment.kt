package com.bruwus.wastetracker.ui.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentCalculateBinding
import com.bruwus.wastetracker.ui.calculate.questions.RadioQuestionFragment
import com.bruwus.wastetracker.ui.calculate.questions.SliderQuestionFragment

class CalculateFragment : Fragment() {
    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!

    // TODO: Delete and replace for db questions
    private var questions = listOf(
        SliderQuestionFragment(1, "How often do you recycle?"),
        RadioQuestionFragment(2, "Do you own an electric car?"),
        SliderQuestionFragment(3, "How often do you reuse?")
    )
    private var i = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)

        activity?.supportFragmentManager?.commit {
            i = 0
            replace(R.id.calculate_linear_layout, questions[i], "fragment_question_${i+1}")
            setReorderingAllowed(true)
        }

        binding.backButton.setOnClickListener {
            if (i > 0) {
                i--

                checkIfShowBackButton(i)

                activity?.supportFragmentManager?.commit {
                    replace(R.id.calculate_linear_layout, questions[i], "fragment_question_${i + 1}")
                    setReorderingAllowed(true)
                }
            }
        }

        binding.nextButton.setOnClickListener {
            i++

            checkIfShowBackButton(i)

            if (i == questions.size) {
                findNavController().navigate(R.id.action_navigation_calculate_to_resultsFragment)
            } else {
                activity?.supportFragmentManager?.commit {
                    replace(R.id.calculate_linear_layout, questions[i], "fragment_question_${i + 1}")
                    setReorderingAllowed(true)
                }
            }
        }

        return binding.root
    }

    private fun checkIfShowBackButton(pos: Int) {
        if (pos < 1) {
            binding.backButton.visibility = View.GONE
        } else {
            binding.backButton.visibility = View.VISIBLE
        }
    }
}