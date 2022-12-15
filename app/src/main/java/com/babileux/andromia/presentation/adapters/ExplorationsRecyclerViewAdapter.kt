package com.babileux.andromia.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babileux.andromia.R
import com.babileux.andromia.core.DateHelper
import com.babileux.andromia.databinding.ItemExplorationBinding
import com.babileux.andromia.domain.models.Element
import com.babileux.andromia.domain.models.Exploration
import com.bumptech.glide.Glide

class ExplorationsRecyclerViewAdapter(
    var explorations: List<Exploration> = listOf(),
    private val onExplorationClick: (Exploration) -> Unit) :RecyclerView.Adapter<ExplorationsRecyclerViewAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplorationsRecyclerViewAdapter.ViewHolder {
        return ViewHolder(ItemExplorationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ExplorationsRecyclerViewAdapter.ViewHolder, position: Int) {
        val exploration = explorations[position]
        holder.bind(exploration)

        holder.itemView.setOnClickListener {
            //onExplorationClick(exploration)
        }

    }
    override fun getItemCount() = explorations.size


    inner class ViewHolder(private val binding: ItemExplorationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exploration: Exploration) {
            with(binding){
                if(!exploration.creatureHasBeenFought){
                    btnCombattre.text = binding.root.context.getString(R.string.combattre)
                    btnCombattre.isEnabled=true
                    btnCombattre.isClickable=true
                } else {
                    btnCombattre.isEnabled=false
                    btnCombattre.isClickable=false
                    btnCombattre.text = binding.root.context.getString(R.string.Combattue)
                }
                txvExploDDate.text = DateHelper.formatISODate(exploration.explorationDate)
                txvExploDestination.text = exploration.destination
                if(exploration.vault != null) {
                    inoxTxv.text = exploration.vault.inox.toString()
                    exploration.vault.elements.forEach { e->
                        applyElementValue(e, binding)
                    }
                }
                if(exploration.creature != null){
                    nameCreature.text = exploration.creature.name
                    Glide.with(binding.root.context).load(exploration.creature.asset).into(binding.creatureEnemyImg)
                } else {
                    nameCreature.text = "Aucune"
                    creatureEnemyImg.setImageResource(R.drawable.none)
                    btnCombattre.isEnabled=false
                    btnCombattre.isClickable=false
                }
                setNotUseElement(binding)
                btnCombattre.setOnClickListener {
                    onExplorationClick(exploration)
                }
            }
        }
    }



    fun applyElementValue(e : Element, binding : ItemExplorationBinding){
        when(e.element) {
            "A" -> binding.textViewA.text = "+${e.quantity}"
            "B" -> binding.textViewB.text = "+" + e.quantity.toString()
            "E" -> binding.textViewE.text = "+" + e.quantity.toString()
            "Ex" -> binding.textViewEx.text = "+" + e.quantity.toString()
            "Fr" -> binding.textViewFr.text = "+" + e.quantity.toString()
            "G" -> binding.textViewG.text = "+" + e.quantity.toString()
            "I" -> binding.textViewI.text = "+" + e.quantity.toString()
            "Ja" -> binding.textViewJa.text = "+" + e.quantity.toString()
            "K" -> binding.textViewK.text = "+" + e.quantity.toString()
            "L" -> binding.textViewL.text = "+" + e.quantity.toString()
            "No" -> binding.textViewNo.text = "+" + e.quantity.toString()
            "Q" -> binding.textViewQ.text = "+" + e.quantity.toString()
            "Sm" -> binding.textViewSm.text = "+" + e.quantity.toString()
            "Ve" -> binding.textViewVe.text = "+" + e.quantity.toString()
            "Wu" -> binding.textViewWu.text = "+" + e.quantity.toString()
            "Xu" -> binding.textViewXu.text = "+" + e.quantity.toString()
            "Ye" -> binding.textViewYe.text = "+" + e.quantity.toString()
            "Z" -> binding.textViewZ.text = "+" + e.quantity.toString()

        }
    }

    fun setNotUseElement(binding : ItemExplorationBinding) {
        if(binding.textViewA.text == "") {
            binding.textViewA.text = "0"
        }
        if(binding.textViewB.text == "") {
            binding.textViewB.text = "0"
        }
        if(binding.textViewE.text == "") {
            binding.textViewE.text = "0"
        }
        if(binding.textViewEx.text == "") {
            binding.textViewEx.text = "0"
        }
        if(binding.textViewFr.text == "") {
            binding.textViewFr.text = "0"
        }
        if(binding.textViewG.text == "") {
            binding.textViewG.text = "0"
        }
        if(binding.textViewI.text == "") {
            binding.textViewI.text = "0"
        }
        if(binding.textViewJa.text == "") {
            binding.textViewJa.text = "0"
        }
        if(binding.textViewK.text == "") {
            binding.textViewK.text = "0"
        }
        if(binding.textViewL.text == "") {
            binding.textViewL.text = "0"
        }
        if(binding.textViewNo.text == "") {
            binding.textViewNo.text = "0"
        }
        if(binding.textViewQ.text == "") {
            binding.textViewQ.text = "0"
        }
        if(binding.textViewSm.text == "") {
            binding.textViewSm.text = "0"
        }
        if(binding.textViewVe.text == "") {
            binding.textViewVe.text = "0"
        }
        if(binding.textViewWu.text == "") {
            binding.textViewWu.text = "0"
        }
        if(binding.textViewXu.text == "") {
            binding.textViewXu.text = "0"
        }
        if(binding.textViewYe.text == "") {
            binding.textViewYe.text = "0"
        }
        if(binding.textViewZ.text == "") {
            binding.textViewZ.text = "0"
        }
    }
}


