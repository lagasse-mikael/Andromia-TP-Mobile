package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Explorateur (
    val username:String="",
    val password:String="",
    val email:String="",

    val vault: Vault = Vault(),
    val explorations: List<Exploration> = listOf(),
    val location:String="",
    val combatCreature:Creature ?= null,
    val tokens: UserConnected = UserConnected()
)
