package mughalasim.my.cv.ui.adapter

import mughalasim.my.cv.data.models.DescriptionModel
import mughalasim.my.cv.data.models.ExperienceModel
import mughalasim.my.cv.data.models.ReferenceModel
import mughalasim.my.cv.data.models.SkillModel

data class RecyclerData (
    val title: String? = null,
    val description: DescriptionModel? = null,
    val skill : SkillModel? = null,
    val work : ExperienceModel? = null,
    val education : ExperienceModel? = null,
    val reference : ReferenceModel? = null,
    val type: AdapterType
    )

enum class AdapterType {
    TITLE, DESCRIPTION, SKILL, WORK, EDUCATION, REFERENCE
}

