package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Creature(
    val stats: Stats,
    val crypto: Crypto,
    val books: List<String> = listOf(),
    val kernel: List<String> = listOf(),
    val archiveIndex: Int,
    val name: String="",
    val uuid: String="",
    val affinity: String="",
    val essence: Int,
    val asset: String=""
):java.io.Serializable
