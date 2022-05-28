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
import androidx.navigation.fragment.findNavController
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.databinding.FragmentLearnBinding
import com.bruwus.wastetracker.ui.calculate.questions.QuestionFragment
import com.bruwus.wastetracker.ui.learn.data.RecyclerViewData
import com.bruwus.wastetracker.ui.learn.recyclerview.HorizontalRecyclerViewFragment
import com.bruwus.wastetracker.utils.general.makeToast

class LearnFragment : Fragment() {
    private var _binding: FragmentLearnBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: LearnViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearnBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[LearnViewModel::class.java]

        val titles = listOf(
            getString(R.string.learn_types_of_waste),
            getString(R.string.learn_recycle_tips),
            getString(R.string.learn_print_3d)
        )

        val fragments = mutableListOf(
            HorizontalRecyclerViewFragment(),
            HorizontalRecyclerViewFragment(),
            HorizontalRecyclerViewFragment()
        )

        titles.forEachIndexed { index, s ->
            val args = Bundle()
            args.putString("title", s)
            fragments[index].arguments = args
        }

        fragments.forEachIndexed { index, fragment ->
            val currentFragment = activity?.supportFragmentManager?.findFragmentByTag("fragment_$index")

            if (currentFragment == null)
                activity?.supportFragmentManager?.commit {
                    add(R.id.linear_layout, fragment, "fragment_$index")
                    setReorderingAllowed(true)
                }
            else
                fragments[index] = currentFragment as HorizontalRecyclerViewFragment
        }

        viewModel.wasteTypes.observe(viewLifecycleOwner) { wasteTypes ->
            setFragmentData(fragments[0], wasteTypes)
        }

        viewModel.recycleTips.observe(viewLifecycleOwner) { recycleTips ->
            setFragmentData(fragments[1], recycleTips)
        }

        viewModel.tools3D.observe(viewLifecycleOwner) { tools3D ->
            setFragmentData(fragments[2], tools3D)
        }

        viewModel.initViewModel()

        return binding.root
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
            makeToast(requireActivity(), getString(R.string.learn_no_waste_types_found), Toast.LENGTH_LONG)
        }
    }
}