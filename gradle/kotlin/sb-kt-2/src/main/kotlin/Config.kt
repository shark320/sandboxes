package com.vpavlov.kt

import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data class Config(
    val map: MutableMap<String, String> = mutableMapOf()
)
