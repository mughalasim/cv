package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.SkillEntity
import cv.domain.entities.getFakeSkills
import mughalasim.my.cv.ui.theme.padding_screen

@Preview(showBackground = true)
@Composable
fun SkillWidget(
    skills: List<SkillEntity> = getFakeSkills()
){
    repeat(skills.size){
        Column(Modifier.fillMaxWidth().padding(start = padding_screen, end = padding_screen)) {
            TextSmall(skills[it].title)
            TextRegular(skills[it].description, Modifier.padding(bottom = padding_screen))
        }
    }
}