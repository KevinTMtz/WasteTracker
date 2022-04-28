package com.example.wastetracker.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wastetracker.databinding.FragmentHomeBinding
import com.example.wastetracker.ui.home.adapter.HomeAdapter
import com.example.wastetracker.ui.home.data.HomeDataProvider


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val homeRecyclerView : RecyclerView = binding.homeRecyclerView
        val homeAdapter = HomeAdapter(HomeDataProvider.getData())

        homeRecyclerView.layoutManager = LinearLayoutManager(activity)
        homeRecyclerView.adapter = homeAdapter

        homeAdapter.setOnItemClickListener(object: HomeAdapter.ClickListener {
            override fun onItemClick(view: View, pos: Int) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                startActivity(browserIntent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}