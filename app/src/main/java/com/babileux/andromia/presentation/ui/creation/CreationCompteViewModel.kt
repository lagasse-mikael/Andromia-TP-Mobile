package com.babileux.andromia.presentation.ui.creation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Explorateur
import com.babileux.andromia.domain.models.UserConnected
import kotlinx.coroutines.launch

class CreationCompteViewModel(application: Application): AndroidViewModel(application) {
    private val loginRepository = LoginRepository(application)

    private var _newExplorateurResponse = MutableLiveData<Resource<Explorateur>>()
    val newExplorateurResponse : LiveData<Resource<Explorateur>> get() = _newExplorateurResponse

    init {

    }

     fun createUser(username: String, password : String, email : String) {

         viewModelScope.launch {
             val newExplorateur = Explorateur(username, password, email)
            _newExplorateurResponse.value = loginRepository.createExplorateur(newExplorateur)
         }

    }

    fun save (tokens: UserConnected, username: String, nbInox: Int, location : String, combatCreatureUUID: String) {
        viewModelScope.launch {
            loginRepository.save(tokens, username, nbInox, location, combatCreatureUUID)
        }
    }
}