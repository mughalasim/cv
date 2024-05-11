package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.SkillEntity
import kotlinx.serialization.Serializable

@Keep
@Suppress("unused")
@Serializable
data class SkillModel(
    val title: String,
    val description: String,
) {
    constructor() : this("", "")
}

fun SkillModel.toSkillEntity() =
    SkillEntity(
        title = this.title,
        description = this.description,
    )
