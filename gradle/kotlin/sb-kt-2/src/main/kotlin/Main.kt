package com.vpavlov.kt

import com.akuleshov7.ktoml.file.TomlFileWriter
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.serializer


@OptIn(InternalSerializationApi::class)
fun main() {
    val config = Config()
    config.map["test"] = "com.vpavlov.kt.Config"
    TomlFileWriter().encodeToFile(
        serializer = Config.serializer(),
        value = config,
        tomlFilePath = "config-descriptor.toml"
    )
}