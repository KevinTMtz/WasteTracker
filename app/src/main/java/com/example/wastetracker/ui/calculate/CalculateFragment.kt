package com.example.wastetracker.ui.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.example.wastetracker.R
import com.example.wastetracker.databinding.FragmentCalculateBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)

        /*
        val sliderFragment = SliderQuestionFragment(1, "How often do you recycle?")
        val radioFragment = RadioQuestionFragment(2, "Do you own an electric car?")

        activity?.supportFragmentManager?.commit {
            add(R.id.linear_layout, sliderFragment, "fragment_question_1")
            add(R.id.linear_layout, radioFragment, "fragment_question_2")
            for (i in 3..15){
                val fragment = SliderQuestionFragment(i, "Question #${i}")
                add(R.id.linear_layout, fragment, "fragment_question_${i}")
            }

            setReorderingAllowed(true)
        }
         */
        activity?.supportFragmentManager?.commit {
            add(R.id.calculate_container, questions[i], "fragment_question_${i+1}")
            setReorderingAllowed(true)
        }

        binding.nextButton.setOnClickListener {
            i++
            if (i == questions.size) {
                findNavController().navigate(R.id.action_navigation_calculate_to_resultsFragment)
            } else {
                activity?.supportFragmentManager?.commit {
                    replace(R.id.calculate_container, questions[i], "fragment_question_${i + 1}")
                    setReorderingAllowed(true)
                }
            }
        }

        return binding.root
    }
}