package com.babileux.andromia.presentation.ui.creatures

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.repositories.CreatureRepository
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Creature
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class CreaturesViewModel (application: Application): AndroidViewModel(application)  {
    private val creatureRepository = CreatureRepository()
    private val loginRepository = LoginRepository(application)

    private val _creatures = MutableLiveData<LoadingResource<List<Creature>>>()
    val creatures : LiveData<LoadingResource<List<Creature>>> get() = _creatures

    init {
        viewModelScope.launch {
            val tokens = loginRepository.userConnected.first()
            creatureRepository.retrieveCreatureExplorer(tokens.access_token).collect(){
                _creatures.value = it
            }
        }
    }
}