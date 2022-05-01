package com.example.wastetracker.ui.calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.example.wastetracker.R
import com.example.wastetracker.databinding.FragmentRadioQuestionBinding
import com.example.wastetracker.databinding.FragmentSliderQuestionBinding

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
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checked = group.findViewById<RadioButton>(checkedId)
        }

        return binding.root
    }

}