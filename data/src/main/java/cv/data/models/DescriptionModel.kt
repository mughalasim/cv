package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.DescriptionEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Suppress("unused")
@Serializable
data class DescriptionModel(
    val address: String,
    @SerialName("full_name")
    val full_name: String,
    val links: List<LinkModel>,
    @SerialName("area_code")
    val area_code: Int,
    val email: String,
    val phone: Long,
    @SerialName("position_title")
    val position_title: String,
) {
    constructor() : this("", "", listOf(), 0, "", 0, "")
}

fun DescriptionModel.toDescriptionEntity() =
    DescriptionEntity(
        address = this.address,
        full_name = this.full_name,
        links = this.links.map { it.toLinkEntity() },
        area_code = this.area_code,
        email = this.email,
        phone = this.phone,
        position_title = this.position_title,
    )