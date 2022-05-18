package com.bruwus.wastetracker.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentAccountBinding
import com.bruwus.wastetracker.utils.navigation.exitMainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
            logout()
        }

        binding.accountGoToUpdateButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_account_to_update_account)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Firebase.auth.currentUser?.let { user ->
            binding.accountNameTextView.text = user.displayName
            binding.accountEmailTextView.text = user.email
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun logout() {
        Firebase.auth.signOut()

        activity?.let { exitMainActivity(it) }
    }
}