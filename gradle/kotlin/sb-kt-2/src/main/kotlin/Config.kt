package com.vpavlov.kt

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val strVal: String = "test-string"
)
