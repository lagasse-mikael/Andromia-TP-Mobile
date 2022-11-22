package com.babileux.andromia.presentation.ui.creatures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.babileux.andromia.R
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.notifyAllItemChanged
import com.babileux.andromia.databinding.FragmentListCreaturesBinding
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.presentation.adapters.CreatureRecyclerViewAdapter


class CreaturesFragment : Fragment(R.layout.fragment_list_creatures) {

    private val binding: FragmentListCreaturesBinding by viewBinding()
    private val viewModel: CreaturesViewModel by viewModels()
    private val creatureRecycleViewAdapter = CreatureRecyclerViewAdapter(listOf())
    private lateinit var ctlMainActivity: ConstraintLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ctlMainActivity = requireActivity().findViewById(R.id.ctlMainActivity)

        binding.rcvCreature.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = creatureRecycleViewAdapter
        }

        viewModel.creatures.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is LoadingResource.Loading -> {

                }
                is LoadingResource.Success -> {
                    creatureRecycleViewAdapter.creatures = it.data!!
                    creatureRecycleViewAdapter.notifyAllItemChanged()
                }
            }
        }
    }
}