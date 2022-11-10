package com.babileux.andromia.presentation.ui.login

import android.content.ClipData.newIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.babileux.andromia.R
import com.babileux.andromia.databinding.ActivityLoginBinding
import com.babileux.andromia.presentation.MainActivity
import com.babileux.andromia.presentation.ui.creation.CreationCompteActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSkipLogin.setOnClickListener{
            val intent = MainActivity.newIntent(this)
            startActivity(intent)
        }


        binding.btnConnecter.setOnClickListener {
            
            when (binding.usernameField.text.toString().trim().isEmpty() || binding.passwordField.text!!.toString().trim().isEmpty()) {
                true -> {
                    Toast.makeText(this, "Champs vide", Toast.LENGTH_SHORT).show()
                }
                false -> {
                    Toast.makeText(this, "Login in", Toast.LENGTH_SHORT).show()

                }
            }



        }

        binding.btnCreer.setOnClickListener {
            val creationCompteActivityIntent = CreationCompteActivity.newIntent(this)
            startActivity(creationCompteActivityIntent)
            
        }
    }




}