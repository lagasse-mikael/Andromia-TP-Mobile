package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Creature(
    val Stats: Stats,
    val Crypto: Crypto,
    val Books: List<String> = listOf(),
    val Kernel: List<String> = listOf(),
    val achivementIndex: Int,
    val name: String="",
    val uuid: String="",
    val affinity: String="",
    val essence: Int,
    val asset: String=""
)
