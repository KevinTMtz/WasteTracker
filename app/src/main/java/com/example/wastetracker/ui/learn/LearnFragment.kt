package com.example.wastetracker.ui.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.wastetracker.R
import com.example.wastetracker.databinding.FragmentLearnBinding
import com.example.wastetracker.ui.learn.data.LearnDataProvider


class LearnFragment : Fragment() {
    private var _binding: FragmentLearnBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearnBinding.inflate(inflater, container, false)

        val fragment1 = HorizontalRecyclerViewFragment("Types of waste", LearnDataProvider.getData())
        val fragment2 = HorizontalRecyclerViewFragment("Recycle", LearnDataProvider.getData())
        val fragment3 = HorizontalRecyclerViewFragment("Print 3D", LearnDataProvider.getData())

        activity?.supportFragmentManager?.commit {
            add(R.id.linear_layout, fragment1, "fragment_1")
            add(R.id.linear_layout, fragment2, "fragment_2")
            add(R.id.linear_layout, fragment3, "fragment_3")
            setReorderingAllowed(true)
        }

        return binding.root
    }
}