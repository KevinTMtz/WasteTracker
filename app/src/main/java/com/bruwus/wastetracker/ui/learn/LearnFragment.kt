package com.bruwus.wastetracker.ui.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentLearnBinding
import com.bruwus.wastetracker.ui.learn.recyclerview.HorizontalRecyclerViewFragment
import com.bruwus.wastetracker.utils.general.makeToast


class LearnFragment : Fragment() {
    private var _binding: FragmentLearnBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: LearnViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearnBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[LearnViewModel::class.java]

        val fragment1 = HorizontalRecyclerViewFragment("Types of waste")
        val fragment2 = HorizontalRecyclerViewFragment("Recycle tips")
        val fragment3 = HorizontalRecyclerViewFragment("Print 3D tools")

        activity?.supportFragmentManager?.commit {
            add(R.id.linear_layout, fragment1, "fragment_1")
            add(R.id.linear_layout, fragment2, "fragment_2")
            add(R.id.linear_layout, fragment3, "fragment_3")
            setReorderingAllowed(true)
        }

        viewModel.wasteTypes.observe(viewLifecycleOwner) { wasteTypes ->
            if (wasteTypes.isNotEmpty()) {
                fragment1.adapter.setData(wasteTypes)
                fragment1.adapter.notifyDataSetChanged()
            } else {
                activity?.supportFragmentManager?.commit {
                    remove(fragment1)
                    setReorderingAllowed(true)
                }
                makeToast(requireActivity(), "No waste types found", Toast.LENGTH_LONG)
            }
        }

        viewModel.recycleTips.observe(viewLifecycleOwner) { recycleTips ->
            if (recycleTips.isNotEmpty()) {
                fragment2.adapter.setData(recycleTips)
                fragment2.adapter.notifyDataSetChanged()
            } else {
                activity?.supportFragmentManager?.commit {
                    remove(fragment2)
                    setReorderingAllowed(true)
                }
                makeToast(requireActivity(), "No recycle tips found", Toast.LENGTH_LONG)
            }
        }

        viewModel.tools3D.observe(viewLifecycleOwner) { tools3D ->
            if (tools3D.isNotEmpty()) {
                fragment3.adapter.setData(tools3D)
                fragment3.adapter.notifyDataSetChanged()
            } else {
                activity?.supportFragmentManager?.commit {
                    remove(fragment3)
                    setReorderingAllowed(true)
                }
                makeToast(requireActivity(), "No 3D tools founds", Toast.LENGTH_LONG)
            }
        }

        viewModel.initViewModel()

        return binding.root
    }
}