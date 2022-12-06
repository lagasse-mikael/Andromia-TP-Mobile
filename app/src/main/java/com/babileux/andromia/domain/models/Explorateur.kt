package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Explorateur (
    val username:String="",
    val password:String="",
    val email:String="",
    val location:String="",
    val vault: Vault = Vault(),
    val explorations: List<Exploration> = listOf(),
    val tokens: UserConnected = UserConnected()
)
