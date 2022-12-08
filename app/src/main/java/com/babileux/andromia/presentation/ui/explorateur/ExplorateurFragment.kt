package com.babileux.andromia.presentation.ui.explorateur

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.babileux.andromia.R
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.databinding.FragmentExplorateurBinding
import com.babileux.andromia.presentation.adapters.VaultRecyclerViewAdapter
import com.babileux.andromia.core.notifyAllItemChanged
import com.babileux.andromia.presentation.ui.login.LoginActivity


class ExplorateurFragment : Fragment(R.layout.fragment_explorateur) {

    private val binding: FragmentExplorateurBinding by viewBinding()
    private val viewModel: ExplorateurViewModel by viewModels()
    private val vaultRecycleViewAdapter = VaultRecyclerViewAdapter(listOf())


    private lateinit var ctlMainActivity: ConstraintLayout //ctl = constraintLayoout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ctlMainActivity = requireActivity().findViewById(R.id.ctlMainActivity)

        binding.rcvVault.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = vaultRecycleViewAdapter
        }

        viewModel.userConnected.observe(viewLifecycleOwner) {
            binding.txvUsername.text = it.username
            binding.txvInox.text = it.nbInox.toString()
            if(it.location.isNotEmpty()){
                binding.txvLocation.text = it.location
            }
        }



        viewModel.exploraterVault.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is LoadingResource.Loading -> {

                }
                is LoadingResource.Success -> {
                    vaultRecycleViewAdapter.elements = it.data!!.elements
                    vaultRecycleViewAdapter.notifyAllItemChanged()
                }
            }
        }
        binding.btnDeconnecter.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

        }
    }

}