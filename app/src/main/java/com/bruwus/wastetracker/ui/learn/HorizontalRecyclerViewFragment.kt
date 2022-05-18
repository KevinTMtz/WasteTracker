package com.bruwus.wastetracker.ui.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentHorizontalRecyclerViewBinding
import com.bruwus.wastetracker.ui.learn.adapter.HorizontalRecyclerViewAdapter

class HorizontalRecyclerViewFragment(private val title: String, private val data: List<String>) : Fragment() {
    private lateinit var binding: FragmentHorizontalRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHorizontalRecyclerViewBinding.inflate(inflater, container, false)

        binding.sectionTitleTextView.text = title

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView : RecyclerView = binding.recyclerView
        val adapter = HorizontalRecyclerViewAdapter(data)

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: HorizontalRecyclerViewAdapter.ClickListener {
            override fun onItemClick(view: View, pos: Int) {
                findNavController().navigate(R.id.action_navigation_learn_to_navigation_information)
            }
        })
    }
}