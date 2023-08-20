package cv.domain.entities

import androidx.annotation.Keep

@Keep
@Suppress("unused")
data class SkillEntity(
    val title: String,
    val description: String
){
    constructor() : this("", "")
}

fun getFakeSkills() = listOf(
    SkillEntity(title = "Skill title 1", description = "Some description 1"),
    SkillEntity(title = "Skill title 2", description = "Some description 2"),
    SkillEntity(title = "Skill title 3", description = "Some description 3"),
    SkillEntity(title = "Skill title 4", description = "Some description 4"),
)