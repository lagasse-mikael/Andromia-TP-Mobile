package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Vault(
    val inox:Int,
    val elements: List<Element>
)
