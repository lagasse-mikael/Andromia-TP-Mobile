package com.babileux.andromia.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babileux.andromia.R
import com.babileux.andromia.databinding.ItemElementBinding
import com.babileux.andromia.domain.models.Element


class VaultRecyclerViewAdapter(var elements: List<Element>)
    : RecyclerView.Adapter<VaultRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_element,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
    override fun getItemCount() = elements.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemElementBinding.bind(view)


        fun bind(element: Element) {
            binding.txvElementName.text = element.name
            binding.txvQuantityElement.text = binding.root.context.getString(element.quantity)
            //TODO: GLIDE POUR LIMAGE
        }
    }

}