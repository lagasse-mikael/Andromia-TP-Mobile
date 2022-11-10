package com.babileux.andromia.presentation.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Explorateur
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val loginRepository = LoginRepository()

    private var _explorateurResponse = MutableLiveData<Resource<Explorateur>>()
    val explorateurResponse : LiveData<Resource<Explorateur>> get() = _explorateurResponse

    init {

    }

    fun LogUser(username: String, password : String) {

        viewModelScope.launch {
            val explorateur = Explorateur(username, password)

            _explorateurResponse.value = loginRepository.retrieve(explorateur)

        }

    }
}