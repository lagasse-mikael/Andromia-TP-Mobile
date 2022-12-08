package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Combat(
    val explorateurUsername:String="",
    val explorateurCreature: Creature,
    val foundCreature: Creature,
    val combatDate: String,
    val userWon: Boolean = false
)
