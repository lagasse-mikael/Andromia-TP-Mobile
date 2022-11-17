package com.babileux.andromia.core

import com.babileux.andromia.domain.models.Exploration

object Constants {
    object BaseURL {
        private const val BASE_API = "http://10.0.2.2:4200" //Travaille en localhost
        //private const val BASE_API = "https://api.andromia.science" // travail en prod
        const val EXPLORERS = "${BASE_API}/explorers"
        const val VAULT = "${BASE_API}/explorers/vault"
        const val LOGIN = "${BASE_API}/explorers/login"
        const val CREATURES = "${BASE_API}/explorers/creatures"
        const val EXPLORATION = "${BASE_API}/explorations/"

    }
}