package com.babileux.andromia.presentation.adapters

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.babileux.andromia.R
import com.babileux.andromia.domain.models.Creature
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import com.babileux.andromia.core.loadFromResource
import com.babileux.andromia.databinding.ItemCreatureBinding
import com.babileux.andromia.domain.models.Element
import com.bumptech.glide.Glide


class CreatureRecyclerViewAdapter(
    var creatures: List<Creature> = listOf()) : RecyclerView.Adapter<CreatureRecyclerViewAdapter.ViewHolder>()
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

                txvValueLife.text = creature.stats.life.toString()
                txvValuePower.text = creature.stats.power.toString()
                txvShieldValue.text = creature.stats.shield.toString()
                txvValueSpeed.text = creature.stats.speed.toString()

                val subStart = creature.crypto.hash.substring(0,2)
                val subEnd = creature.crypto.hash.substring(62,64)
                val hashList = creature.crypto.hash.substring(2,62).chunked(6)

                binding.txvHashStart.text = subStart
                binding.txvHashEnd.text = subEnd

                //binding.imvKernelFirst.loadFromResource(requireContext(), "element_"+creature.kernel[0].lowercase())
                //binding.imvKernelSecond.loadFromResource(requireContext(), "element_"+creature.kernel[1].lowercase())
                //binding.imvKernelThird.loadFromResource(requireContext(), "element_"+creature.kernel[2].lowercase())
                //binding.imvKernelFourth.loadFromResource(requireContext(), "element_"+creature.kernel[3].lowercase())
                //binding.imvKernelFifth.loadFromResource(requireContext(), "element_"+creature.kernel[4].lowercase())

                binding.txvHashFirst.setBackgroundColor(Color.parseColor("#"+hashList[0]))
                binding.txvHashSecond.setBackgroundColor(Color.parseColor("#"+hashList[1]))
                binding.txvHashThird.setBackgroundColor(Color.parseColor("#"+hashList[2]))
                binding.txvHashFourth.setBackgroundColor(Color.parseColor("#"+hashList[3]))
                binding.txvHashFifth.setBackgroundColor(Color.parseColor("#"+hashList[4]))
                binding.txvHashSixth.setBackgroundColor(Color.parseColor("#"+hashList[5]))
                binding.txvHashSeventh.setBackgroundColor(Color.parseColor("#"+hashList[6]))
                binding.txvHashEight.setBackgroundColor(Color.parseColor("#"+hashList[7]))
                binding.txvHashNinth.setBackgroundColor(Color.parseColor("#"+hashList[8]))
                binding.txvHashTenth.setBackgroundColor(Color.parseColor("#"+hashList[9]))

                Glide.with(root.context).load(creature.books[0]).into((imvBookFirst));
                Glide.with(root.context).load(creature.affinity).into(imvAffinityCreature)

                Glide.with(root.context)
                    .load(creature.asset)
                    .into(imvCreature)
            //TODO: GLIDE POUR LIMAGE
            }
        }
    }


}