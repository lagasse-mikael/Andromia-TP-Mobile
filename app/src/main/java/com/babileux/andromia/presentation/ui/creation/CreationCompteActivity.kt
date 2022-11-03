package com.babileux.andromia.presentation.ui.creation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.babileux.andromia.R
import com.babileux.andromia.databinding.ActivityCreationCompteBinding
import com.babileux.andromia.databinding.ActivityLoginBinding
import com.babileux.andromia.presentation.MainActivity
import com.babileux.andromia.presentation.ui.login.LoginViewModel

class CreationCompteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreationCompteBinding
    private val viewModel: CreationCompteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreationCompteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, CreationCompteActivity::class.java)
        }
    }
}