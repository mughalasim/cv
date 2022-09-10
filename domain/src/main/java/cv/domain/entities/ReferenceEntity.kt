package cv.domain.entities

data class ReferenceEntity(
    val name: String,
    val company: String,
    val contact: String
){
    constructor() : this("", "", "") {}
}

fun getFakeReferences() = listOf(
    ReferenceEntity(
        name = "Test Name 1",
        company = "Test company 1",
        contact = "test@test.com"
    ),
    ReferenceEntity(
        name = "Test Name 2",
        company = "Test company 2",
        contact = "test@test.com"
    ),
    ReferenceEntity(
        name = "Test Name 3",
        company = "Test company 3",
        contact = "test@test.com"
    )
)