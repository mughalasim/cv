package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.ReferenceEntity
import kotlinx.serialization.Serializable

@Keep
@Suppress("unused")
@Serializable
data class ReferenceModel(
    val name: String,
    val company: String,
    val contact: String,
) {
    constructor() : this("", "", "")
}

fun ReferenceModel.toReferenceEntity() =
    ReferenceEntity(
        name = this.name,
        company = this.company,
        contact = this.contact,
    )
