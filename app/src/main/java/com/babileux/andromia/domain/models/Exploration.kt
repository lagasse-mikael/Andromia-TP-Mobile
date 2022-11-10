package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Exploration(
    val explorationDate: String="",
    val destination: String,
    val affinity: String,
    val vault : Vault,
    val creature: Creature
)
