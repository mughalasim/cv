package cv.domain.entities

import androidx.annotation.Keep

@Keep
@Suppress("unused")
data class ExperienceEntity(
    val title: String,
    val startDate: String,
    val endDate: String,
    val positionTitle: String,
    val location: String,
    val description: String,
    val links: List<LinkEntity>,
    val ongoing: Boolean = false,
) {
    constructor() : this("", "", "", "", "", "", listOf(), false)
}

fun getFakeExperience() =
    listOf(
        ExperienceEntity(
            title = "Title 1",
            startDate = "2022-09-01",
            endDate = "2021-09-01",
            positionTitle = "Test position",
            location = "Test Location",
            description = "Sample description",
            links = getFakeLinks().take(3),
            ongoing = true,
        ),
        ExperienceEntity(
            title = "Title 2",
            startDate = "2022-08-01",
            endDate = "2021-08-01",
            positionTitle = "Test position",
            location = "Test Location",
            description = "Sample description",
            links = getFakeLinks().take(4),
            ongoing = false,
        ),
        ExperienceEntity(
            title = "Title 3",
            startDate = "2022-05-01",
            endDate = "2021-05-01",
            positionTitle = "Test position",
            location = "Test Location",
            description = "Sample description",
            links = getFakeLinks().take(8),
            ongoing = false,
        ),
    )
