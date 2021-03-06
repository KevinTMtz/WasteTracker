package com.bruwus.wastetracker.ui.calculate.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentNumberQuestionBinding
import com.bruwus.wastetracker.ui.calculate.data.QuestionArgs

class NumberQuestionFragment: QuestionFragment() {

    private lateinit var binding: FragmentNumberQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumberQuestionBinding.inflate(inflater, container, false)

        questionArgs = arguments?.getSerializable("questionArgs") as QuestionArgs?

        binding.questionNumber.text = getString(R.string.calculate_question_num, "#", questionArgs?.number)
        binding.questionText.text = questionArgs?.text

        binding.numberInput.doOnTextChanged { text, _, _, _ ->
            answer = text.toString().toDoubleOrNull()
        }

        return binding.root
    }
}