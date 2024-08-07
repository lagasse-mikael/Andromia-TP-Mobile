package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Vault(
    val inox:Int = 0,
    val elements: List<Element> = listOf()
)
