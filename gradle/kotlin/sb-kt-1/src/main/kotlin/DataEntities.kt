
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlChildrenName
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue
import java.time.LocalDate

@Serializable
data class DataRoot(
    val version: String,
    val data: Data
)

@Serializable
data class Data(
    val submitCategories: SubmitCategories,

    val submits: Submits
)

@Serializable
data class SubmitCategories(
    val submitCategories: List<SubmitCategoryElement>
)

@Serializable
data class SubmitCategoryElement(
    val id: Long,
    val title: String,
    val colorId: Long? = null,
    val iconId: Long? = null,
    val lastUsed: Long? = null
)

@Serializable
@SerialName("SubmitsTmp")
@XmlSerialName("SubmitsTmp")
data class Submits(
    val submits: List<SubmitElement>
)

@Serializable
data class SubmitElement(
    val id: Long,
    val title: String,
    val description: String? = null,
    val amount: Double,
    val timestamp: Long,
    val date: Long,
    val categoryId: Long? = null,
    val type: String
)
