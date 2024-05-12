package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.LinkEntity
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LinkModel(
    val text: String,
    val url: String,
)

fun LinkModel.toLinkEntity() =
    LinkEntity(
        text = this.text,
        url = this.url,
    )
