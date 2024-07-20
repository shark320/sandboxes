package com.vpavlov.kt

import com.akuleshov7.ktoml.file.TomlFileWriter
import com.akuleshov7.ktoml.source.TomlSourceReader
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.serializer

val str = """[map]
    "test\client" = "config/client""""
@OptIn(InternalSerializationApi::class)
fun main() {
    val config = Config()
//    config.map["test.client"] = "test/client"
//    TomlFileWriter().encodeToFile(
//        serializer = Config.serializer(),
//        value = config,
//        tomlFilePath = "config-descriptor.toml"
//    )
    println(config::class.qualifiedName)
//    println(TomlSourceReader().decodeFromString(Config.serializer(),str))
}