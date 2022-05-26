package com.bruwus.wastetracker.ui.calculate.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentNumberQuestionBinding

class NumberQuestionFragment(
    private val number: Int,
    private val question: String,
    multiplier: Double = 1.0
) : QuestionFragment(multiplier) {

    private lateinit var binding: FragmentNumberQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumberQuestionBinding.inflate(inflater, container, false)

        binding.questionNumber.text = getString(R.string.calculate_question_num, "#", number)
        binding.questionText.text = question

        binding.numberInput.doOnTextChanged { text, _, _, _ ->
            answer = text.toString().toDouble()
        }

        return binding.root
    }

}