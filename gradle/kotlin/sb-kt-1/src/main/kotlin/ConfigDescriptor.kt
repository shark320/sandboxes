import kotlinx.serialization.Serializable

@Serializable
data class ConfigDescriptor(
    val data: MutableList<ConfigFile> = mutableListOf()
)

@Serializable
data class ConfigFile(
    val path: String,
    val `class`: String
)
