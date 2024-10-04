package cv.domain.entities

data class DescriptionEntity(
    val address: String,
    val fullName: String,
    val links: List<LinkEntity>,
    val areaCode: Int,
    val email: String,
    val phone: Long,
    val positionTitle: String,
    val profilePictureUrl: String,
)

@Suppress("detekt.MaxLineLength")
fun getFakeDescription() =
    DescriptionEntity(
        address = "London, United Kingdom",
        fullName = "Asim Mughal",
        links = getFakeLinks(),
        areaCode = 44,
        email = "test@test.com",
        phone = 7470997045,
        positionTitle = "Mobile Software Engineer",
        profilePictureUrl = "https://firebasestorage.googleapis.com/v0/b/personal-cv-mughalasim.appspot.com/o/profile_pic.JPG?alt=media&token=7cef53bb-aef5-4f76-8bca-26a50d64aecb"
    )
