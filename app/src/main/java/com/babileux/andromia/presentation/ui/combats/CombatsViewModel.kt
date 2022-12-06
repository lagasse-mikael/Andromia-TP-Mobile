package com.babileux.andromia.presentation.ui.combats

import android.app.Application
import androidx.lifecycle.*
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.repositories.CombatRepository
import com.babileux.andromia.data.repositories.CreatureRepository
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.domain.models.Exploration
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CombatsViewModel (application: Application, private val exploration: Exploration): AndroidViewModel(application)   {
    private val combatRepository = CombatRepository()


    private val _creatures = MutableLiveData<LoadingResource<List<Creature>>>()
    val creatures : LiveData<LoadingResource<List<Creature>>> get() = _creatures

    init {

    }

    class Factory(private val application: Application ,private val exploration: Exploration) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, Exploration::class.java).newInstance(application, exploration)
        }
    }

}