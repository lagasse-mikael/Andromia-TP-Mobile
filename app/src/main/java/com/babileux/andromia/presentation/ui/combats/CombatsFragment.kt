package com.babileux.andromia.presentation.ui.combats

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.babileux.andromia.R
import com.babileux.andromia.databinding.FragmentCombatsBinding
import com.bumptech.glide.Glide

class CombatsFragment : Fragment(R.layout.fragment_combats) {
    private val binding: FragmentCombatsBinding by viewBinding()
    private val args : CombatsFragmentArgs by navArgs()

    private val viewModel: CombatsViewModel by viewModels {
        CombatsViewModel.Factory(requireActivity().application, args.exploration)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtLifePointEnemy.text = args.exploration.creature!!.stats.life.toString()
        binding.txtPowerPointEnemy.text = args.exploration.creature!!.stats.power.toString()
        binding.txtShieldPointEnemy.text = args.exploration.creature!!.stats.shield.toString()
        binding.txtSpeedPointEnemy.text = args.exploration.creature!!.stats.speed.toString()
        Glide.with(requireContext())
            .load(args.exploration.creature!!.asset)
            .into(binding.creatureEnemyImg)
    }
}