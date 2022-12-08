package com.babileux.andromia.presentation.ui.combats

import android.app.Application
import androidx.lifecycle.*
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.repositories.CombatRepository
import com.babileux.andromia.data.repositories.CreatureRepository
import com.babileux.andromia.data.repositories.ExplorateurRepository
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.domain.models.Exploration
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CombatsViewModel (application: Application, private val exploration: Exploration): AndroidViewModel(application)   {
    private val combatRepository = CombatRepository()
    private val explorateurRepository = ExplorateurRepository()
    private val loginRepository = LoginRepository(application)

    private val _combatCreature = MutableLiveData<LoadingResource<Creature>>()
    val combatCreature: LiveData<LoadingResource<Creature>> get() = _combatCreature

    init {
        viewModelScope.launch{
            val tokens = loginRepository.userConnected.first()
            explorateurRepository.retrieveExplorerCombatCreature(tokens.access_token).collect {
                _combatCreature.value = it
            }
        }
    }

    class Factory(private val application: Application ,private val exploration: Exploration) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, Exploration::class.java).newInstance(application, exploration)
        }
    }
    fun generateFight(enemy : Creature, buddy : Creature) {

        viewModelScope.launch {
            val tokens = loginRepository.userConnected.first()
            combatRepository.generateFight(enemy, buddy,  tokens.access_token, tokens.username)
        }

    }
}