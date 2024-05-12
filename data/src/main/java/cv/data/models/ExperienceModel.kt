package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.ExperienceEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ExperienceModel(
    val title: String,
    @SerialName("start_date")
    val start_date: String,
    @SerialName("end_date")
    val end_date: String,
    @SerialName("position_title")
    val position_title: String,
    val location: String,
    val description: String,
    val links: List<LinkModel>,
    val ongoing: Boolean = false,
)

fun ExperienceModel.toExperienceEntity() =
    ExperienceEntity(
        title = this.title,
        startDate = this.start_date,
        endDate = this.end_date,
        positionTitle = this.position_title,
        location = this.location,
        description = this.description,
        links = this.links.map { it.toLinkEntity() },
        ongoing = this.ongoing,
    )
