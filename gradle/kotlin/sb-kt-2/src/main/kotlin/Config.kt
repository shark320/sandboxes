package com.vpavlov.kt

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val map: MutableMap<String, String> = mutableMapOf()
)
