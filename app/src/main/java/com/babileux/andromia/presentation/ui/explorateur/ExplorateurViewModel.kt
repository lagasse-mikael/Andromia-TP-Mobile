package com.babileux.andromia.presentation.ui.explorateur

import android.app.Application
import androidx.lifecycle.*
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.repositories.ExplorateurRepository
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Element
import com.babileux.andromia.domain.models.Explorateur
import com.babileux.andromia.domain.models.Vault
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ExplorateurViewModel (application: Application): AndroidViewModel(application) {

    private val explorateurRepository = ExplorateurRepository()
    private val loginRepository = LoginRepository(application)

    private val _exploraterVault = MutableLiveData<LoadingResource<Vault>>()
    val exploraterVault: LiveData<LoadingResource<Vault>> get() = _exploraterVault

    private val _userConnected = MutableLiveData<String>()
    val userConnected: LiveData<String> get() = _userConnected

    init {
        viewModelScope.launch {
           val tokens =  loginRepository.userConnected.first()
            explorateurRepository.retriveExplorerVault(tokens.access_token).collect{
                _exploraterVault.value = it
            }
        }

    }


}