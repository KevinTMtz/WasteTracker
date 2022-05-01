package com.example.wastetracker.ui.calculate.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.wastetracker.databinding.FragmentRadioQuestionBinding

class RadioQuestionFragment(private val number: Int, private val question: String) : Fragment() {

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
        // TODO: Add radio buttons programmatically
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checked = group.findViewById<RadioButton>(checkedId)
            // TODO: Find a way to have a value and text properties for radio button
        }

        return binding.root
    }

}