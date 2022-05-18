package com.bruwus.wastetracker.ui.home

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentHomeBinding
import com.bruwus.wastetracker.ui.home.adapter.HomeAdapter
import com.bruwus.wastetracker.ui.home.data.HomeDataProvider


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
                val url = "http://www.google.com"
                val builder = CustomTabsIntent.Builder()

                val shareIcon = BitmapFactory.decodeResource(
                    binding.root.context.resources,
                    R.drawable.ic_baseline_share_24,
                )
                val shareIntent = Intent(
                    binding.root.context,
                    ShareBroadcastReceiver::class.java,
                )
                val pendingIntent = PendingIntent.getBroadcast(
                    binding.root.context,
                    0,
                    shareIntent,
                    PendingIntent.FLAG_IMMUTABLE,
                )
                builder.setActionButton(shareIcon, "Share via...", pendingIntent)

                val params = CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(binding.root.context.getColor(R.color.blue))
                    .build()
                builder.setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, params)

                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(binding.root.context, Uri.parse(url))
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}