package com.bruwus.wastetracker.ui.calculate.questions

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentNumberQuestionBinding
import com.bruwus.wastetracker.databinding.FragmentSliderQuestionBinding

class NumberQuestionFragment(
    private val number: Int,
    private val question: String,
    multiplicator: Double = 1.0
) : QuestionFragment(multiplicator) {

    private lateinit var binding: FragmentNumberQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumberQuestionBinding.inflate(inflater, container, false)

        binding.questionNumber.text = "Question #${number}"
        binding.questionText.text = question

        binding.numberInput.doOnTextChanged { text, _, _, _ ->
            answer = text.toString().toDouble()
        }

        return binding.root
    }

}