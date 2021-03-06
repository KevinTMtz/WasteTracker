package com.bruwus.wastetracker.ui.account.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentUpdateAccountBinding
import com.bruwus.wastetracker.utils.general.makeToast
import com.bruwus.wastetracker.utils.navigation.exitMainActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class UpdateAccountFragment : Fragment() {
    lateinit var binding: FragmentUpdateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateAccountBinding.inflate(layoutInflater)

        binding.updateAccountButton.setOnClickListener {
            updateAccount()
        }

        binding.deleteAccountButton.setOnClickListener {
            deleteAccount()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        Firebase.auth.currentUser?.let { user ->
            binding.updateNameText.setText(user.displayName)
            binding.updateEmailText.setText(user.email)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    private fun updateAccount() {
        val name = binding.updateNameText.text.toString()
        val email = binding.updateEmailText.text.toString()
        val password = binding.updatePasswordText.text.toString()
        val confirmPassword = binding.updateConfirmPasswordText.text.toString()

        if (name.isEmpty() || email.isEmpty()) {
            makeToast(requireActivity(), getString(R.string.validation_input_all_information), Toast.LENGTH_SHORT)
            return
        }

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            makeToast(requireActivity(), getString(R.string.validation_input_password), Toast.LENGTH_SHORT)
            return
        }

        if (password != confirmPassword) {
            makeToast(requireActivity(), getString(R.string.validation_passwords_not_match), Toast.LENGTH_SHORT)
            return
        }

        Firebase.auth.currentUser?.let { user ->
            user.reauthenticate(EmailAuthProvider.getCredential(user.email!!, password))
                .addOnSuccessListener {
                    user.updateEmail(email)
                        .addOnSuccessListener {
                            val profileUpdates = userProfileChangeRequest {
                                displayName = name
                            }

                            user.updateProfile(profileUpdates)
                                .addOnSuccessListener {
                                    makeToast(requireActivity(), getString(R.string.update_account_updated), Toast.LENGTH_SHORT)
                                    findNavController().popBackStack()
                                }
                                .addOnFailureListener {
                                    makeToast(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                                }
                        }
                        .addOnFailureListener {
                            makeToast(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                        }
                }
                .addOnFailureListener {
                    makeToast(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                }
        }
    }

    private fun deleteAccount() {
        val password = binding.updatePasswordText.text.toString()
        val confirmPassword = binding.updateConfirmPasswordText.text.toString()

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            makeToast(requireActivity(), getString(R.string.validation_input_password), Toast.LENGTH_SHORT)
            return
        }

        if (password != confirmPassword) {
            makeToast(requireActivity(), getString(R.string.validation_passwords_not_match), Toast.LENGTH_SHORT)
            return
        }

        Firebase.auth.currentUser?.let { user ->
            user.reauthenticate(EmailAuthProvider.getCredential(user.email!!, password))
                .addOnSuccessListener {
                    user.delete()
                        .addOnSuccessListener {
                            makeToast(requireActivity(), getString(R.string.update_account_deleted), Toast.LENGTH_SHORT)

                            activity?.let { exitMainActivity(it) }
                        }
                        .addOnFailureListener {
                            makeToast(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                        }
                }
                .addOnFailureListener {
                    makeToast(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                }
        }
    }
}