package com.babileux.andromia.presentation.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babileux.andromia.R
import com.babileux.andromia.databinding.ItemElementBinding
import com.babileux.andromia.domain.models.Element
import com.bumptech.glide.Glide


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
        val element = elements[position]
        holder.bind(element)
    }
    override fun getItemCount() = elements.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemElementBinding.bind(view)
        @SuppressLint("DiscouragedApi")
        fun bind(element: Element) {
            val imgRes = "element_${element.element}"
            val ressourceId = binding.root.resources.getIdentifier(imgRes.lowercase(), "drawable", binding.root.context.packageName)

            binding.txvElementName.text = element.element
            binding.txvQuantityElement.text = element.quantity.toString()
            binding.imvElementLogo.setImageResource(ressourceId)

        }
    }

}