package com.babileux.andromia.presentation.ui.explorateur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.babileux.andromia.R
import com.babileux.andromia.databinding.FragmentExplorateurBinding
import com.babileux.andromia.databinding.FragmentListCreaturesBinding
import com.babileux.andromia.presentation.adapters.VaultRecyclerViewAdapter
import com.babileux.andromia.presentation.ui.creatures.CreaturesViewModel

class ExplorateurFragment : Fragment() {

    private val binding: FragmentExplorateurBinding by viewBinding()
    private val viewModel: ExplorateurViewModel by viewModels()
    private val vaultRecycleViewAdapter = VaultRecyclerViewAdapter(listOf())

    private lateinit var ctlMainActivity: ConstraintLayout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctlMainActivity = requireActivity().findViewById(R.id.ctlMainActivity)


        binding.rcvVault.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = vaultRecycleViewAdapter
        }
    }


}