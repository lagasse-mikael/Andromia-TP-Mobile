package com.babileux.andromia.presentation.ui.combats

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.util.Log.INFO
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.babileux.andromia.R
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.Resource
import com.babileux.andromia.core.notifyAllItemChanged
import com.babileux.andromia.databinding.FragmentCombatsBinding
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.presentation.MainActivity
import com.bumptech.glide.Glide
import kotlin.math.log

class CombatsFragment : Fragment(R.layout.fragment_combats) {
    private val binding: FragmentCombatsBinding by viewBinding()
    private val args : CombatsFragmentArgs by navArgs()
    lateinit var buddy : Creature

    private val viewModel: CombatsViewModel by viewModels {
        CombatsViewModel.Factory(requireActivity().application, args.exploration)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.combatResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Combat effectué", Toast.LENGTH_LONG).show()
                     if(it.data!!.userWon)
                         binding.txtResultat.text ="Vous avez Gagné"
                    else
                        binding.txtResultat.text ="Vous avez perdu lol";
                }
            }
        }

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
                    buddy = it.data!!
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
        binding.btnFight.setOnClickListener {
            val enemy = args.exploration.creature

            //val buddy =
            if (enemy != null) {
                viewModel.generateFight(buddy,enemy)
            }
        }
    }
}