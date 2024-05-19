package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.DescriptionEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class DescriptionModel(
    val address: String,
    @SerialName("full_name")
    val fullName: String,
    val links: List<LinkModel>,
    @SerialName("area_code")
    val areaCode: Int,
    val email: String,
    val phone: Long,
    @SerialName("position_title")
    val positionTitle: String,
)

fun DescriptionModel.toDescriptionEntity() =
    DescriptionEntity(
        address = this.address,
        fullName = this.fullName,
        links = this.links.map { it.toLinkEntity() },
        areaCode = this.areaCode,
        email = this.email,
        phone = this.phone,
        positionTitle = this.positionTitle,
    )
