package com.babileux.andromia.presentation.ui.creation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Explorateur
import kotlinx.coroutines.launch

class CreationCompteViewModel(application: Application): AndroidViewModel(application) {
    private val loginRepository = LoginRepository()

    private var _newExplorateurResponse = MutableLiveData<Resource<Explorateur>>()
    val newExplorateurResponse : LiveData<Resource<Explorateur>> get() = _newExplorateurResponse

    init {

    }

     fun createUser(username: String, password : String, email : String) {

         viewModelScope.launch {
             val newExplorateur = Explorateur(username, email, password)

            _newExplorateurResponse.value = loginRepository.createExplorateur(newExplorateur)




         }

    }
}