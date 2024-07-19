package com.vpavlov.kt

import com.akuleshov7.ktoml.file.TomlFileWriter
import kotlinx.serialization.serializer

fun main() {
    TomlFileWriter().encodeToFile(Config.serializer(), Config(), "config.toml")
}