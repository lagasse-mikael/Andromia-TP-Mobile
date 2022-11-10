package com.babileux.andromia.presentation.ui.combats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.babileux.andromia.R
import com.babileux.andromia.databinding.FragmentListCreaturesBinding
import com.babileux.andromia.presentation.ui.creatures.CreaturesViewModel

class CombatsFragment : Fragment() {
    private val binding: FragmentListCreaturesBinding by viewBinding()
    private val viewModel: CreaturesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_combats, container, false)
    }

}