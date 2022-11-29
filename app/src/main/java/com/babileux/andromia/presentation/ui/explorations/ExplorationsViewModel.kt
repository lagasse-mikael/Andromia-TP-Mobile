package com.babileux.andromia.presentation.ui.creatures

import android.app.Application
import androidx.lifecycle.*
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.repositories.ExplorateurRepository
import com.babileux.andromia.data.repositories.ExplorationRepository
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Element
import com.babileux.andromia.domain.models.Exploration
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.content.QRContent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ExplorationsViewModel(application: Application): AndroidViewModel(application){
    private val explorationRepository = ExplorationRepository()

    private val _exploration = MutableLiveData<LoadingResource<List<Exploration>>>()
    val exploration: LiveData<LoadingResource<List<Exploration>>> get() = _exploration
    private val loginRepository = LoginRepository(application)

    init {
        viewModelScope.launch {
            val tokens = loginRepository.userConnected.first()
            explorationRepository.retriveExploration(tokens.access_token).collect{
                _exploration.value = it
            }
        }
    }

    fun GenerateExploration(qrCode: String) {
        viewModelScope.launch {
            val tokens =  loginRepository.userConnected.first()
            explorationRepository.GenerateExploration(tokens.access_token, qrCode)
        }

    }
}