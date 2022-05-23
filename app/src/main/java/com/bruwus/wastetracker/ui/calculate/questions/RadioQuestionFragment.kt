package com.bruwus.wastetracker.ui.calculate.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.bruwus.wastetracker.databinding.FragmentRadioQuestionBinding

class RadioQuestionFragment(private val number: Int,
                            private val question: String,
                            multiplicator: Double = 1.0) : QuestionFragment(multiplicator) {

    private lateinit var binding: FragmentRadioQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRadioQuestionBinding.inflate(inflater, container, false)

        binding.questionNumber.text = "Question #${number}"
        binding.questionText.text = question
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checked = group.findViewById<RadioButton>(checkedId)
            answer = checked.getTag().toString().toDouble()
        }

        return binding.root
    }

}