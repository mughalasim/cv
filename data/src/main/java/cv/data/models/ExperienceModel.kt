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
    val startDate: String,
    @SerialName("end_date")
    val endDate: String,
    @SerialName("position_title")
    val positionTitle: String,
    val location: String,
    val description: String,
    val links: List<LinkModel>? = null,
    val ongoing: Boolean = false,
)

fun ExperienceModel.toExperienceEntity() =
    ExperienceEntity(
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        positionTitle = this.positionTitle,
        location = this.location,
        description = this.description,
        links = this.links?.map { it.toLinkEntity() },
        ongoing = this.ongoing,
    )
