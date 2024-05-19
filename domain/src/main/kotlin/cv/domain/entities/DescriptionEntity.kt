package cv.domain.entities

data class DescriptionEntity(
    val address: String,
    val fullName: String,
    val links: List<LinkEntity>,
    val areaCode: Int,
    val email: String,
    val phone: Long,
    val positionTitle: String,
)

fun getFakeDescription() =
    DescriptionEntity(
        address = "London, United Kingdom",
        fullName = "Asim Mughal",
        links = getFakeLinks(),
        areaCode = 44,
        email = "test@test.com",
        phone = 7470997045,
        positionTitle = "Mobile Software Engineer",
    )
