import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML
import java.time.LocalDate


fun generateData(count: Int): DataRoot {
    val submitsList = mutableListOf<SubmitElement>()
    val categoriesList = mutableListOf<SubmitCategoryElement>()
    for(id in 1..count){
        submitsList.add(
            SubmitElement(
                id = id.toLong(),
                title =  "submit_$id",
                date = LocalDate.now().toEpochDay(),
                type = ESubmitType.OUTCOME.name,
                amount = id*100.0,
                timestamp = System.currentTimeMillis(),
                categoryId = id.toLong(),
                description = if (id%2==0) "test_description_$id" else null
            )
        )

        categoriesList.add(
            SubmitCategoryElement(
                id = id.toLong(),
                title = "category_$id",
                iconId = id.toLong(),
                colorId = if (id%2==0) id.toLong() else null,
                lastUsed = if (id%2==0) System.currentTimeMillis() else null,
            )
        )
    }
    return DataRoot(
        version = "1",
        data = Data(
            submitCategories = SubmitCategories(
                submitCategories = categoriesList
            ),
            submits = Submits(
                submits = submitsList
            )
        )
    )
}

fun main(args: Array<String>) {
    val data = generateData(3)
    val xml = XML{
        indentString = "\t"
    }
    val str = xml.encodeToString(data)
    println(str)
    println(xml.decodeFromString(DataRoot.serializer(), str))
}