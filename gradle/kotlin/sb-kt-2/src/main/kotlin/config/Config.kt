package com.vpavlov.kt.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val strVal: String = "test-string"
)
