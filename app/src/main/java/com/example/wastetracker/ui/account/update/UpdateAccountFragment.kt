package com.example.wastetracker.ui.account.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wastetracker.R
import com.example.wastetracker.databinding.FragmentUpdateAccountBinding

class UpdateAccountFragment : Fragment() {
    lateinit var binding: FragmentUpdateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateAccountBinding.inflate(layoutInflater)

        binding.updateAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_update_account_to_navigation_account)
        }

        binding.updateCancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_update_account_to_navigation_account)
        }

        return binding.root
    }
}