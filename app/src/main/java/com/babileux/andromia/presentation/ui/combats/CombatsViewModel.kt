package com.babileux.andromia.presentation.ui.combats

import android.app.Application
import androidx.lifecycle.*
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.repositories.*
import com.babileux.andromia.domain.models.Combat
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.domain.models.Explorateur
import com.babileux.andromia.domain.models.Exploration
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CombatsViewModel (application: Application, private val exploration: Exploration): AndroidViewModel(application)   {
    private val combatRepository = CombatRepository()
    private val explorateurRepository = ExplorateurRepository()
    private val explorationRepository = ExplorationRepository();
    private val loginRepository = LoginRepository(application)

    private val _combatResponse = MutableLiveData<Resource<Combat>>()
    val combatResponse : LiveData<Resource<Combat>> get() = _combatResponse

    private val _combatCreature = MutableLiveData<LoadingResource<Creature>>()
    val combatCreature: LiveData<LoadingResource<Creature>> get() = _combatCreature

    private val _exploration = MutableLiveData<Resource<Exploration>>()
    val lexploration: LiveData<Resource<Exploration>> get() = _exploration

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
    fun generateFight(buddy : Creature,enemy : Creature ) {
        viewModelScope.launch {
            val tokens = loginRepository.userConnected.first()
            _combatResponse.value = combatRepository.generateFight(buddy,enemy,tokens.access_token, tokens.username)
        }
    }
    fun setResult(exploration: Exploration) {
        viewModelScope.launch {
            val tokens = loginRepository.userConnected.first()
            _exploration.value = explorationRepository.setResult(tokens.access_token,exploration);
        }
    }
}