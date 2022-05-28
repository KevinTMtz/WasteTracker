package com.bruwus.wastetracker.ui.calculate.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentRadioQuestionBinding
import com.bruwus.wastetracker.ui.calculate.data.QuestionArgs

class RadioQuestionFragment: QuestionFragment() {
    private lateinit var binding: FragmentRadioQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioQuestionBinding.inflate(inflater, container, false)

        questionArgs = arguments?.getSerializable("questionArgs") as QuestionArgs?

        binding.questionNumber.text = getString(R.string.calculate_question_num, "#", questionArgs?.number)
        binding.questionText.text = questionArgs?.text
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checked = group.findViewById<RadioButton>(checkedId)
            answer = checked.tag.toString().toDouble()
        }

        return binding.root
    }
}