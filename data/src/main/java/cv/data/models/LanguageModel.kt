package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.LanguageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LanguageModel(
    @SerialName("error_internet_connection")
    val errorInternetConnection: String,
    @SerialName("error_invalid_link")
    val errorInvalidLink: String,
    @SerialName("error_server")
    val errorServer: String,
    @SerialName("txt_address")
    val txtAddress: String,
    @SerialName("txt_contact_info")
    val txtContactInfo: String,
    @SerialName("txt_education")
    val txtEducation: String,
    @SerialName("txt_email")
    val txtEmail: String,
    @SerialName("txt_links")
    val txtLinks: String,
    @SerialName("txt_present")
    val txtPresent: String,
    @SerialName("txt_references")
    val txtReferences: String,
    @SerialName("txt_skills")
    val txtSkills: String,
    @SerialName("txt_telephone")
    val txtTelephone: String,
    @SerialName("txt_version")
    val txtVersion: String,
    @SerialName("txt_work_experience")
    val txtWorkExperience: String,
    @SerialName("plural_month")
    val pluralMonth: List<String>,
    @SerialName("plural_year")
    val pluralYear: List<String>,
)

fun LanguageModel.toLanguageEntity(locale: String) =
    LanguageEntity(
        singleTexts =
            hashMapOf(
                Pair("error_internet_connection", errorInternetConnection),
                Pair("error_invalid_link", errorInvalidLink),
                Pair("error_server", errorServer),
                Pair("txt_address", txtAddress),
                Pair("txt_contact_info", txtContactInfo),
                Pair("txt_education", txtEducation),
                Pair("txt_email", txtEmail),
                Pair("txt_links", txtLinks),
                Pair("txt_present", txtPresent),
                Pair("txt_references", txtReferences),
                Pair("txt_skills", txtSkills),
                Pair("txt_telephone", txtTelephone),
                Pair("txt_version", txtVersion),
                Pair("txt_work_experience", txtWorkExperience),
            ),
        pluralTexts =
            hashMapOf(
                Pair("plural_month", pluralMonth.toTypedArray()),
                Pair("plural_month", pluralMonth.toTypedArray()),
            ),
        locale = locale,
    )
