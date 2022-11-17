package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Exploration(
    val _id: String = "",
    val explorationDate: String="",
    val destination: String = "",
    val affinity: String = "",
    val vault : Vault,
    val creature: Creature,
    //val combat : Combat
)
