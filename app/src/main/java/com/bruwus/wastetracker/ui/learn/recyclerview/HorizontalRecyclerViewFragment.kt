package com.bruwus.wastetracker.ui.learn.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentHorizontalRecyclerViewBinding
import com.bruwus.wastetracker.ui.learn.adapter.HorizontalRecyclerViewAdapter

class HorizontalRecyclerViewFragment : Fragment() {
    private lateinit var binding: FragmentHorizontalRecyclerViewBinding

    var adapter: HorizontalRecyclerViewAdapter = HorizontalRecyclerViewAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHorizontalRecyclerViewBinding.inflate(inflater, container, false)

        binding.sectionTitleTextView.text = arguments?.getString("title")

        val recyclerView : RecyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: HorizontalRecyclerViewAdapter.ClickListener {
            override fun onItemClick(view: View, pos: Int) {
                val bundle = bundleOf("data" to adapter.getData(pos))

                findNavController().navigate(R.id.action_navigation_learn_to_navigation_information, bundle)
            }
        })

        return binding.root
    }
}