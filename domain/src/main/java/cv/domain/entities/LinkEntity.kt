package cv.domain.entities

@Suppress("unused")
data class LinkEntity(
    val text: String,
    val url: String
){
    constructor() : this("", "")
}

fun getFakeLinks() = listOf(
    LinkEntity(text = "Google 1", url = "https://google.com"),
    LinkEntity(text = "Google 2", url = "https://google.com"),
    LinkEntity(text = "Google 3", url = "https://google.com"),
)