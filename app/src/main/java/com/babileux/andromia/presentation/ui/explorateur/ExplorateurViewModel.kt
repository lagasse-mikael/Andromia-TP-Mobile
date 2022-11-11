package com.babileux.andromia.presentation.ui.explorateur

import androidx.lifecycle.*
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.repositories.ExplorateurRepository
import com.babileux.andromia.domain.models.Element
import kotlinx.coroutines.launch

class ExplorateurViewModel: ViewModel() {

    private val explorateurRepository = ExplorateurRepository()

    private val _exploraterVault = MutableLiveData<LoadingResource<List<Element>>>()
    val exploraterVault: LiveData<LoadingResource<List<Element>>> get() = _exploraterVault

    init {
        viewModelScope.launch {
            explorateurRepository.retriveExplorerVault().collect{
                _exploraterVault.value = it
            }
        }
    }


}