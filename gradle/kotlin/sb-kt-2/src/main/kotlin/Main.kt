package com.vpavlov.kt

import com.akuleshov7.ktoml.file.TomlFileWriter
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.serializer


@OptIn(InternalSerializationApi::class)
fun main() {
    TomlFileWriter().encodeToFile(
        serializer = Class.forName("com.vpavlov.kt.Config").kotlin.serializer() as SerializationStrategy<Config>,
        value = Config(),
        tomlFilePath = "config.toml"
    )
    println()
}