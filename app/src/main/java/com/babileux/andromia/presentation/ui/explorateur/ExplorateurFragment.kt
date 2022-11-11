package com.babileux.andromia.presentation.ui.explorateur

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.babileux.andromia.R
import com.babileux.andromia.databinding.FragmentExplorateurBinding
import com.babileux.andromia.presentation.adapters.VaultRecyclerViewAdapter

class ExplorateurFragment : Fragment(R.layout.fragment_explorateur) {

    private val binding: FragmentExplorateurBinding by viewBinding()
    private val viewModel: ExplorateurViewModel by viewModels()
    private val vaultRecycleViewAdapter = VaultRecyclerViewAdapter(listOf())

    private lateinit var ctlMainActivity: ConstraintLayout //ctl = constraintLayoout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ctlMainActivity = requireActivity().findViewById(R.id.ctlMainActivity)

        binding.rcvVault.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = vaultRecycleViewAdapter
        }

    }


}