package cv.domain.entities

import androidx.annotation.Keep

@Keep
@Suppress("unused")
data class LinkEntity(
    val text: String,
    val url: String,
) {
    constructor() : this("", "")
}

fun getFakeLinks() =
    listOf(
        LinkEntity(text = "Google 1", url = "https://google.com"),
        LinkEntity(text = "Google 2", url = "https://google.com"),
        LinkEntity(text = "Google 3", url = "https://google.com"),
        LinkEntity(text = "Google 4", url = "https://google.com"),
        LinkEntity(text = "Google 5", url = "https://google.com"),
        LinkEntity(text = "Google 6", url = "https://google.com"),
        LinkEntity(text = "Google 7", url = "https://google.com"),
        LinkEntity(text = "Google 8", url = "https://google.com"),
        LinkEntity(text = "Google 9", url = "https://google.com"),
        LinkEntity(text = "Google 10", url = "https://google.com"),
    )
