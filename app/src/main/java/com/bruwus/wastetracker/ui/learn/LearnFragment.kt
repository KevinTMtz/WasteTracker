package com.bruwus.wastetracker.ui.learn

import android.annotation.SuppressLint
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
import com.bruwus.wastetracker.ui.learn.data.RecyclerViewData
import com.bruwus.wastetracker.ui.learn.recyclerview.HorizontalRecyclerViewFragment
import com.bruwus.wastetracker.utils.general.makeToast

class LearnFragment : Fragment() {
    private var _binding: FragmentLearnBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: LearnViewModel

    private val fragments = mutableListOf<HorizontalRecyclerViewFragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearnBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[LearnViewModel::class.java]

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchAllData()
        loadFragments()
    }

    override fun onPause() {
        super.onPause()

        removeFragments()
    }

    private fun loadFragments() {
        val titles = listOf(
            getString(R.string.learn_types_of_waste),
            getString(R.string.learn_recycle_tips),
            getString(R.string.learn_print_3d)
        )

        titles.forEachIndexed { index, s ->
            val currentFragment = activity?.supportFragmentManager?.findFragmentByTag("fragment_$index")

            if (currentFragment == null)
                fragments.add(HorizontalRecyclerViewFragment())
            else
                fragments.add(currentFragment as HorizontalRecyclerViewFragment)

            activity?.supportFragmentManager?.commit {
                add(R.id.linear_layout, fragments[index], "fragment_$index")
                setReorderingAllowed(true)
            }

            val args = Bundle()
            args.putString("title", s)
            fragments[index].arguments = args

            viewModel.lists[index].observe(viewLifecycleOwner) { recyclerData ->
                setFragmentData(fragments[index], recyclerData)
            }
        }
    }

    private fun removeFragments() {
        for (i in 0..fragments.size) {
            val currentFragment = activity?.supportFragmentManager?.findFragmentByTag("fragment_$i")

            if (currentFragment != null)
                activity?.supportFragmentManager?.commit {
                    remove(currentFragment)
                    setReorderingAllowed(false)
                }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFragmentData(fragment: HorizontalRecyclerViewFragment, data: List<RecyclerViewData>) {
        if (data.isNotEmpty()) {
            fragment.adapter.setData(data)
            fragment.adapter.notifyDataSetChanged()
        } else {
            activity?.supportFragmentManager?.commit {
                remove(fragment)
                setReorderingAllowed(true)
            }
            makeToast(requireActivity(), getString(R.string.learn_no_data_found), Toast.LENGTH_LONG)
        }
    }
}