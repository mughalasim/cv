package cv.domain.entities

data class DescriptionEntity (
    val address: String,
    val full_name: String,
    val links: List<LinkEntity>,
    val area_code: Int,
    val email: String,
    val phone: Long,
    val position_title: String
){
    constructor() : this("", "", listOf(), 0, "", 0, "") {}
}

fun getFakeDescription() = DescriptionEntity(
    address = "London, United Kingdom",
    full_name = "Asim Mughal",
    links = getFakeLinks(),
    area_code = 44,
    email = "test@test.com",
    phone = 7470997045,
    position_title = "Mobile Software Engineer"
)