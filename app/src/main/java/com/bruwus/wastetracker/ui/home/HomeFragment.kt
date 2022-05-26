package com.bruwus.wastetracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.databinding.FragmentHomeBinding
import com.bruwus.wastetracker.ui.home.adapter.HomeAdapter
import com.bruwus.wastetracker.ui.utils.browser.InAppBrowser
import com.bruwus.wastetracker.utils.general.makeToast


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        adapter = HomeAdapter(listOf())

        val homeRecyclerView : RecyclerView = binding.homeRecyclerView
        homeRecyclerView.layoutManager = LinearLayoutManager(activity)
        homeRecyclerView.adapter = adapter

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            if (articles.isNotEmpty()) {
                adapter.setArticles(articles)
                adapter.notifyDataSetChanged()
            } else {
                makeToast(requireActivity(), "No news found", Toast.LENGTH_LONG)
            }
        }

        adapter.setOnItemClickListener(object: HomeAdapter.ClickListener {
            override fun onItemClick(view: View, pos: Int) {
                val url = adapter.getArticle(pos).url
                InAppBrowser.open(binding.root.context, url)
            }
        })

        viewModel.loadArticles()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}