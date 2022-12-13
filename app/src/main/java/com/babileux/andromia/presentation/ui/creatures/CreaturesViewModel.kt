package com.babileux.andromia.presentation.ui.creatures

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.repositories.CreatureRepository
import com.babileux.andromia.data.repositories.ExplorateurRepository
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.domain.models.Explorateur
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class CreaturesViewModel (application: Application): AndroidViewModel(application)  {
    private val creatureRepository = CreatureRepository()
    private val loginRepository = LoginRepository(application)
    private val explorateurRepository = ExplorateurRepository()

    private val _creatures = MutableLiveData<LoadingResource<List<Creature>>>()
    val creatures : LiveData<LoadingResource<List<Creature>>> get() = _creatures

    private val _explorer = MutableLiveData<Resource<Explorateur>>()
    val explorer : LiveData<Resource<Explorateur>> get() = _explorer

    init {
        viewModelScope.launch {
            val user = loginRepository.userConnected.first()

        }
        viewModelScope.launch {
            val tokens = loginRepository.userConnected.first()
            creatureRepository.retrieveCreatureExplorer(tokens.access_token).collect(){
                _creatures.value = it
            }
        }
    }

    fun setCombatCreature(combatCreature : Creature) {
        viewModelScope.launch {
            val tokens = loginRepository.userConnected.first()
                _explorer.value = explorateurRepository.setCombatCreature(tokens.access_token, combatCreature)
        }
    }
}