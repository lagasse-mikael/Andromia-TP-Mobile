package com.babileux.andromia.presentation.ui.creatures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.repositories.ExplorateurRepository
import com.babileux.andromia.data.repositories.ExplorationRepository
import com.babileux.andromia.domain.models.Element
import com.babileux.andromia.domain.models.Exploration
import kotlinx.coroutines.launch

class ExplorationsViewModel : ViewModel() {
    private val explorationRepository = ExplorationRepository()

    private val _exploration = MutableLiveData<LoadingResource<List<Exploration>>>()
    val exploration: LiveData<LoadingResource<List<Exploration>>> get() = _exploration

    init {
        viewModelScope.launch {
            explorationRepository.retriveExploration().collect{
                _exploration.value = it
            }
        }
    }
}