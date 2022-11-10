package com.babileux.andromia.presentation.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.babileux.andromia.R

class ExplorationsRecyclerViewAdapter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_exploration)
    }
}