package com.babileux.andromia.presentation.ui.combats

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.babileux.andromia.R
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.notifyAllItemChanged
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

        viewModel.combatCreature.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is LoadingResource.Loading -> {

                }
                is LoadingResource.Success -> {
                    binding.txtUserLife.text = it.data?.stats?.life.toString()
                    binding.txtUserPower.text = it.data?.stats?.power.toString()
                    binding.txtUserShield.text = it.data?.stats?.shield.toString()
                    binding.txtUserSpeed.text = it.data?.stats?.speed.toString()
                    Glide.with(requireContext())
                        .load(it.data?.asset)
                        .into(binding.imageViewCreatureUser)
                }
            }
        }

        binding.txtLifePointEnemy.text = args.exploration.creature!!.stats.life.toString()
        binding.txtPowerPointEnemy.text = args.exploration.creature!!.stats.power.toString()
        binding.txtShieldPointEnemy.text = args.exploration.creature!!.stats.shield.toString()
        binding.txtSpeedPointEnemy.text = args.exploration.creature!!.stats.speed.toString()
        binding.txvCreatureEnemy.text = args.exploration.creature!!.name
        Glide.with(requireContext())
            .load(args.exploration.creature!!.asset)
            .into(binding.creatureEnemyImg)
    }
}