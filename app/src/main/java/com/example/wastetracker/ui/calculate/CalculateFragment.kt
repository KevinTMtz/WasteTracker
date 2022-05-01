package com.example.wastetracker.ui.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.wastetracker.R
import com.example.wastetracker.databinding.FragmentCalculateBinding

class CalculateFragment : Fragment() {
    private var _binding: FragmentCalculateBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)

        val sliderFragment = SliderQuestionFragment(1, "How often do you recycle?")
        val radioFragment = RadioQuestionFragment(2, "Do you own an electric car?")

        activity?.supportFragmentManager?.commit {
            add(R.id.linear_layout, sliderFragment, "fragment_question_1")
            add(R.id.linear_layout, radioFragment, "fragment_question_2")
            setReorderingAllowed(true)
        }
        return binding.root
    }
}