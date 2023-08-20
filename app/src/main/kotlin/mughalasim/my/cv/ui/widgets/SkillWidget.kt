package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.SkillEntity
import cv.domain.entities.getFakeSkills
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen

@Composable
fun SkillWidget(
    skills: List<SkillEntity>
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = padding_screen, end = padding_screen),
        horizontalAlignment = Alignment.Start
    ) {
        repeat(skills.size){
            TextSmall(text = skills[it].title)
            TextRegular(
                modifier = Modifier.padding(bottom = padding_screen),
                text = skills[it].description
            )
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SkillWidgetPreviewNight(){
    AppThemeComposable {
        SkillWidget(getFakeSkills())
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SkillWidgetPreview(){
    AppThemeComposable {
        SkillWidget(getFakeSkills())
    }
}