package com.babileux.andromia.presentation.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.babileux.andromia.R

class CreatureRecyclerViewAdapter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_creature)
    }
}