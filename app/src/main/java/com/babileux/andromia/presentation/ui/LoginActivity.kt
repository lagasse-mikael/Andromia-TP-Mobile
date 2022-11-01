package com.babileux.andromia.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.babileux.andromia.R
import com.babileux.andromia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)



        binding.btnConnecter.setOnClickListener {
            Toast.makeText(this, "LE bouton a été cliquer", Toast.LENGTH_SHORT).show()
        }
    }




}