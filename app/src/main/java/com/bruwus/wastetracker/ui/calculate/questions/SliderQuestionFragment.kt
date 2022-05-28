package com.bruwus.wastetracker.ui.calculate.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentSliderQuestionBinding
import com.bruwus.wastetracker.ui.calculate.data.QuestionArgs

class SliderQuestionFragment: QuestionFragment() {
    private lateinit var binding: FragmentSliderQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSliderQuestionBinding.inflate(inflater, container, false)

        val labels = mapOf(
            0 to getString(R.string.calculate_likert_never),
            1 to getString(R.string.calculate_likert_hardly_never),
            2 to getString(R.string.calculate_likert_sometimes),
            3 to getString(R.string.calculate_likert_often),
            4 to getString(R.string.calculate_likert_usually),
        )

        questionArgs = arguments?.getSerializable("questionArgs") as QuestionArgs?

        binding.questionNumber.text = getString(R.string.calculate_question_num, "#", questionArgs?.number)
        binding.questionText.text = questionArgs?.text
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