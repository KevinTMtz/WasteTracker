package com.example.wastetracker.ui.calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wastetracker.R
import com.example.wastetracker.databinding.FragmentHorizontalRecyclerViewBinding
import com.example.wastetracker.databinding.FragmentSliderQuestionBinding

class SliderQuestionFragment(private val number: Int, private val question: String) : Fragment() {

    private lateinit var binding: FragmentSliderQuestionBinding
    private var answer: Double? = null
    // TODO: Define a better dict of labels
    private val labels = mapOf(
        0 to "Never",
        1 to "Hardly ever",
        2 to "Sometimes",
        3 to "Often",
        4 to "Usually",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSliderQuestionBinding.inflate(inflater, container, false)

        binding.questionNumber.text = "Question #${number}"
        binding.questionText.text = question
        answer = binding.slider.value.toDouble()

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            answer = value.toDouble()
        }
        binding.slider.setLabelFormatter{ value: Float ->
            return@setLabelFormatter "${labels[value.toInt()]}"
        }

        return binding.root
    }
}