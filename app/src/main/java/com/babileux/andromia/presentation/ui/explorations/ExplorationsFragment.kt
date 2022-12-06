package com.babileux.andromia.presentation.ui.explorations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.babileux.andromia.R
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.notifyAllItemChanged
import com.babileux.andromia.databinding.FragmentListExplorationsBinding
import com.babileux.andromia.domain.models.Exploration
import com.babileux.andromia.presentation.adapters.ExplorationsRecyclerViewAdapter
import com.babileux.andromia.presentation.ui.creatures.ExplorationsViewModel
import com.babileux.andromia.presentation.ui.explorations.ExplorationsFragmentDirections
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.content.QRContent

class ExplorationsFragment : Fragment(R.layout.fragment_list_explorations) {

    private val binding: FragmentListExplorationsBinding by viewBinding()
    private val viewModel: ExplorationsViewModel by viewModels()

    private val quickieActivityLauncher =
        registerForActivityResult(ScanQRCode(), ::handleQuickieResult)

    private lateinit var explorationsRecyclerViewAdapter : ExplorationsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        explorationsRecyclerViewAdapter = ExplorationsRecyclerViewAdapter(listOf(), ::onRecyclerViewExplorationClick)

        binding.rcvExplorations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = explorationsRecyclerViewAdapter
        }
        binding.btnAddExploration.setOnClickListener { quickieActivityLauncher.launch(null) }

        viewModel.exploration.observe(viewLifecycleOwner){
            when(it){
                is LoadingResource.Error -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
                is LoadingResource.Loading -> {
                    binding.rcvExplorations.visibility = View.INVISIBLE
                }
                is LoadingResource.Success -> {
                    if(it.data!!.isNotEmpty()) {
                        explorationsRecyclerViewAdapter.explorations = it.data!!.reversed()
                        explorationsRecyclerViewAdapter.notifyAllItemChanged()
                        binding.rcvExplorations.visibility = View.VISIBLE
                        binding.txvPasExplo.visibility = View.INVISIBLE
                    } else {
                        binding.txvPasExplo.visibility = View.VISIBLE
                    }

                }
            }
        }
    }
    val scanQrCodeLauncher = registerForActivityResult(ScanQRCode()) { result ->
        // handle QRResult
    }

    private fun handleQuickieResult(qrResult: QRResult) {
        when (qrResult) {
            is QRResult.QRSuccess -> {
                Toast.makeText(requireContext(),"Exploration complétée",Toast.LENGTH_LONG).show()
                GenerateExploration(qrResult.content.rawValue)
            }
            is QRResult.QRUserCanceled -> {
                Toast.makeText(
                    requireContext(),
                    "Vous avez annulé l'opération",
                    Toast.LENGTH_LONG
                ).show()
            }
            is QRResult.QRMissingPermission -> {
                Toast.makeText(
                    requireContext(),
                    "Tu n'a pas les permissions",
                    Toast.LENGTH_LONG
                ).show()
            }
            is QRResult.QRError -> {
                Toast.makeText(requireContext(), "Ca marche pas", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun GenerateExploration(qrResult: String) {
        viewModel.GenerateExploration(qrResult)
    }

    fun onRecyclerViewExplorationClick(exploration: Exploration) {
        val action = ExplorationsFragmentDirections.actionNavigationExplorationsToCombatsFragment(exploration)
        findNavController().navigate(action)
    }









}