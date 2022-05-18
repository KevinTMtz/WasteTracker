package com.bruwus.wastetracker.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentAccountBinding
import com.bruwus.wastetracker.ui.LandingPageActivity

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)

        binding.accountLogoutButton.setOnClickListener {
            val intent = Intent(context, LandingPageActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.accountGoToUpdateButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_account_to_update_account)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}