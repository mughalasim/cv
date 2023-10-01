package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.SkillEntity
import cv.domain.entities.getFakeSkills
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_small

@Composable
fun SkillWidget(
    skills: List<SkillEntity>
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        repeat(skills.size){
            Row ( modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(bottom = padding_screen)
            ){
                Column (modifier = Modifier
                    .fillMaxHeight()
                    .width(padding_screen_small)
                    .background(color = AppTheme.colors.highLight)
                ){}
                Column ( modifier = Modifier.padding(start = padding_screen_small)) {
                    TextSmall(text = skills[it].title)
                    TextRegular(text = skills[it].description)
                }
            }
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