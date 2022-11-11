package com.babileux.andromia.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babileux.andromia.databinding.ItemExplorationBinding
import com.babileux.andromia.domain.models.Exploration

class ExplorationsRecyclerViewAdapter(
    var explorations: List<Exploration> = listOf()):RecyclerView.Adapter<ExplorationsRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplorationsRecyclerViewAdapter.ViewHolder {
        return ViewHolder(ItemExplorationBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    }

    override fun getItemCount() = explorations.size


    inner class ViewHolder(private val binding: ItemExplorationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exploration: Exploration) {


        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}


