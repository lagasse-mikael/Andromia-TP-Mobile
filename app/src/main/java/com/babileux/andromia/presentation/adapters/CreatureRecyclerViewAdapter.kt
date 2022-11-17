package com.babileux.andromia.presentation.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.babileux.andromia.R
import com.babileux.andromia.domain.models.Creature
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babileux.andromia.databinding.ItemCreatureBinding
import com.babileux.andromia.domain.models.Element
import com.bumptech.glide.Glide


class CreatureRecyclerViewAdapter(
    var creatures: List<Creature> = listOf(), ) : RecyclerView.Adapter<CreatureRecyclerViewAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_creature, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sauce = creatures[position]
        holder.bind(sauce)
    }

    override fun getItemCount() = creatures.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCreatureBinding.bind(view)


        fun bind(creature: Creature) {
            with(binding){
                nameCreature.text = creature.name
                txvAffinityCreature.text = creature.affinity
                txvLifeCreature.text = creature.stats.life.toString()
                txvPowerCreature.text = creature.stats.power.toString()
                txvShieldCreature.text = creature.stats.shield.toString()
                txvSpeedCreature.text = creature.stats.speed.toString()
                Glide.with(root.context)
                    .load(creature.asset)
                    .into(imvCreature)
            //TODO: GLIDE POUR LIMAGE
            }
        }
    }


}