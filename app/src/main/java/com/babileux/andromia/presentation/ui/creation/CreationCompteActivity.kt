package com.babileux.andromia.presentation.ui.creation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.babileux.andromia.R
import com.babileux.andromia.core.Resource
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

        viewModel.newExplorateurResponse.observe(this) {
            when (it) {
                is Resource.Error -> Toast.makeText(this, "MARCHE PO", Toast.LENGTH_LONG).show()
                is Resource.Success -> Toast.makeText(this, "Créer", Toast.LENGTH_LONG).show()
            }
        }


        binding.btnCreerNew.setOnClickListener {

            when ((binding.usernameField.text.toString().trim().isEmpty() || binding.emailField.text!!.toString().trim().isEmpty()
                    || binding.repeatPasswordField.text!!.toString().trim().isEmpty() || binding.passwordField.text!!.toString().trim().isEmpty())
                        && (binding.repeatPasswordField.text.toString() == binding.passwordField.text.toString())) {
                true -> {
                    Toast.makeText(this, "Ton mot de passe fit po ou ta un champs vide", Toast.LENGTH_SHORT).show()
                }
                false -> {


                    viewModel.createUser(binding.usernameField.text.toString(),
                        binding.passwordField.text.toString(), binding.emailField.text.toString())



                }
            }



        }



    }





    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, CreationCompteActivity::class.java)
        }
    }
}