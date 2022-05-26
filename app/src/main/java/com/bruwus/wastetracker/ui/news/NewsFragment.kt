package com.bruwus.wastetracker.ui.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentHomeBinding
import com.bruwus.wastetracker.ui.news.adapter.NewsAdapter
import com.bruwus.wastetracker.ui.utils.browser.InAppBrowser
import com.bruwus.wastetracker.utils.general.makeToast


class NewsFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        adapter = NewsAdapter(listOf())

        val newsRecyclerView : RecyclerView = binding.newsRecyclerView
        newsRecyclerView.layoutManager = LinearLayoutManager(activity)
        newsRecyclerView.adapter = adapter

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            if (articles.isNotEmpty()) {
                adapter.setArticles(articles)
                adapter.notifyDataSetChanged()
            } else {
                makeToast(requireActivity(), getString(R.string.news_no_data_found), Toast.LENGTH_LONG)
            }
        }

        adapter.setOnItemClickListener(object: NewsAdapter.ClickListener {
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