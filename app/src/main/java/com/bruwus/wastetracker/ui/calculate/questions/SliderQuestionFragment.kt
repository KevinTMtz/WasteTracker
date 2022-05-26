package com.bruwus.wastetracker.ui.calculate.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentSliderQuestionBinding

class SliderQuestionFragment(
    private val number: Int,
    private val question: String,
    multiplier: Double = 1.0
) : QuestionFragment(multiplier) {
    private lateinit var binding: FragmentSliderQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val labels = mapOf(
            0 to getString(R.string.calculate_likert_never),
            1 to getString(R.string.calculate_likert_hardly_never),
            2 to getString(R.string.calculate_likert_sometimes),
            3 to getString(R.string.calculate_likert_often),
            4 to getString(R.string.calculate_likert_usually),
        )

        binding = FragmentSliderQuestionBinding.inflate(inflater, container, false)

        binding.questionNumber.text = getString(R.string.calculate_question_num, "#", number)
        binding.questionText.text = question
        answer = binding.slider.value.toDouble()

        binding.slider.addOnChangeListener { _, value, _ ->
            answer = value.toDouble()
        }
        binding.slider.setLabelFormatter{ value: Float ->
            return@setLabelFormatter "${labels[value.toInt()]}"
        }

        return binding.root
    }
}